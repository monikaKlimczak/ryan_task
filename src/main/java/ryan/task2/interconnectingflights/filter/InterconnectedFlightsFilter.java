package ryan.task2.interconnectingflights.filter;

import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightsWithDates;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterconnectedFlightsFilter {

    public List<InterconnectedFlightWithDates> filterByTimeCriteria(
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
            List<InterconnectedFlightsWithDates> interconnectedFlightsList) {
        List<InterconnectedFlightWithDates> filteredInterconnectedFlights = new ArrayList<>();
        for (InterconnectedFlightsWithDates interconnectedFlights: interconnectedFlightsList) {
            for (FlightWithDate flightDeparture: interconnectedFlights.getFlightsDepartures()) {
                for (FlightWithDate flightArrival: interconnectedFlights.getFlightsArrivals()) {
                    if (meetsTimeCriteria(departureDateTime, arrivalDateTime, flightDeparture, flightArrival)) {
                        filteredInterconnectedFlights.add(
                                new InterconnectedFlightWithDates(flightDeparture, flightArrival,
                                interconnectedFlights.getMiddleAirport()));
                    }
                }
            }
        }
        return filteredInterconnectedFlights;
    }

    private boolean meetsTimeCriteria(
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, FlightWithDate flightDeparture,
            FlightWithDate flightArrival) {
        LocalDateTime flightDepartureDepartureDateTime =
                LocalDateTime.of(flightDeparture.getDate(), flightDeparture.getDepartureTime());
        LocalDateTime flightArrivalArrivalDateTime =
                LocalDateTime.of(flightArrival.getDate(), flightArrival.getArrivalTime());
        LocalDateTime flightDepartureArrivalDateTime =
                LocalDateTime.of(flightDeparture.getDate(), flightDeparture.getArrivalTime());
        LocalDateTime flightArrivalDepartureDateTime =
                LocalDateTime.of(flightArrival.getDate(), flightArrival.getDepartureTime());
        return !flightDepartureDepartureDateTime.isBefore(departureDateTime) &&
                !flightArrivalArrivalDateTime.isAfter(arrivalDateTime)
                && differenceBetweenArrivalAndNextDepartureIsAtLeast2Hours(
                flightDepartureArrivalDateTime, flightArrivalDepartureDateTime);
    }

    private boolean differenceBetweenArrivalAndNextDepartureIsAtLeast2Hours(
            LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        return arrivalDateTime.until(departureDateTime, ChronoUnit.HOURS) >= 2;
    }
}
