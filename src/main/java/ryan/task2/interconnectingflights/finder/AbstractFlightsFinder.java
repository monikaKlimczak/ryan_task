package ryan.task2.interconnectingflights.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ryan.task2.interconnectingflights.converter.FlightsConverter;
import ryan.task2.interconnectingflights.util.JsonUtils;
import ryan.task2.interconnectingflights.RyanairUri;
import ryan.task2.interconnectingflights.api.Day;
import ryan.task2.interconnectingflights.api.Timetable;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.TimetableWithYear;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFlightsFinder {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private JsonUtils jsonUtils;


    protected TimetableWithYear getTimetableForFlights(String departureAirport, String arrivalAirport,
                                                       LocalDateTime departureDateTime) {
        String uri = String.format(RyanairUri.TIMETABLE.getUri(),
                departureAirport, arrivalAirport, departureDateTime.getYear(), departureDateTime.getMonthValue());
        Timetable timetable = jsonUtils.convertToTimeTable(getTimetableResponseDirectFlights(uri));
        return new TimetableWithYear(timetable.getMonth(), timetable.getDays(), departureDateTime.getYear());
    }

    private String getTimetableResponseDirectFlights(String uri) {
        try {
            return restTemplate.getForObject(uri, String.class);
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                return "{}";
            }
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    protected List<FlightWithDate> getFlightsForDays(TimetableWithYear timetable, int departureDay, int arrivalDay) {
        List<FlightWithDate> flights = new ArrayList<>();
        for (int day = departureDay; day <= arrivalDay; day++) {
            flights.addAll(getFlightsForDay(timetable, day));
        }
        return flights;
    }

    protected List<FlightWithDate> getFlightsForDay(TimetableWithYear timetable, int dayOfMonth) {
        FlightsConverter flightsConverter = new FlightsConverter();
        List<Day> days = timetable.getDays();
        if (days == null) {
            return new ArrayList<>();
        }

        for (Day day : days) {
            if (day.getDay() == dayOfMonth) {
                return flightsConverter.convert(day.getFlights(),
                        LocalDate.of(timetable.getYear(), timetable.getMonth(), dayOfMonth));
            }
        }
        return new ArrayList<>();
    }
}
