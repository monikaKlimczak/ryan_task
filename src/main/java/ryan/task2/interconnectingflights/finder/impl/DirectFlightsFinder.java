package ryan.task2.interconnectingflights.finder.impl;

import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.converter.FlightsConverter;
import ryan.task2.interconnectingflights.api.Flight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.TimetableWithYear;
import ryan.task2.interconnectingflights.finder.AbstractFlightsFinder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectFlightsFinder extends AbstractFlightsFinder {

    public List<FlightWithDate> find(Leg expectedLeg) {
        LocalDateTime departureDateTime = expectedLeg.getDepartureDateTime();
        LocalDateTime arrivalDateTime = expectedLeg.getArrivalDateTime();
        TimetableWithYear timetable = getTimetableForFlights(
                expectedLeg.getDepartureAirport(), expectedLeg.getArrivalAirport(), departureDateTime);

        List<FlightWithDate> flightsWithDate = getFlightsForDays(
                timetable, departureDateTime.getDayOfMonth(), arrivalDateTime.getDayOfMonth());

        return flightsWithDate.stream()
                .filter(flight -> meetsTimeCriteria(flight, expectedLeg))
                .collect(Collectors.toList());
    }

    private boolean meetsTimeCriteria(FlightWithDate flight, Leg expectedLeg) {
        LocalDateTime departureDateTime = expectedLeg.getDepartureDateTime();
        LocalDateTime flightDepartureDateTime = LocalDateTime.of(flight.getDate(), flight.getDepartureTime());
        LocalDateTime flightArrivalDateTime = LocalDateTime.of(flight.getDate(), flight.getArrivalTime());
        return !flightDepartureDateTime.isBefore(departureDateTime)
                && !flightArrivalDateTime.isAfter(expectedLeg.getArrivalDateTime());
    }
}
