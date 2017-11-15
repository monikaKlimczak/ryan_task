package ryan.task2.interconnectingflights.converter;

import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.Flight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;

import java.time.LocalDateTime;

@Component
public class DefaultLegConverter {

    public Leg toDirectLeg(Leg expectedLeg, FlightWithDate flight) {
        return new Leg.Builder()
                .departureAirport(expectedLeg.getDepartureAirport())
                .arrivalAirport(expectedLeg.getArrivalAirport())
                .departureDateTime(
                        LocalDateTime.of(flight.getDate(), flight.getDepartureTime()))
                .arrivalDateTime(
                        LocalDateTime.of(flight.getDate(), flight.getArrivalTime()))
                .build();
    }

    public Leg toInterconnectedFirstLeg(Leg expectedLeg, InterconnectedFlightWithDates interconnectedFlight) {
        Flight firstFlight = interconnectedFlight.getFirstFlight();
        return new Leg.Builder()
                .departureAirport(expectedLeg.getDepartureAirport())
                .arrivalAirport(interconnectedFlight.getMiddleAirport())
                .departureDateTime(
                        LocalDateTime.of(interconnectedFlight.getFirstFlight().getDate(), firstFlight.getDepartureTime()))
                .arrivalDateTime(
                        LocalDateTime.of(interconnectedFlight.getFirstFlight().getDate(), firstFlight.getArrivalTime()))
                .build();
    }

    public Leg toInterconnectedSecondLeg(Leg expectedLeg, InterconnectedFlightWithDates interconnectedFlight) {
        Flight secondFlight = interconnectedFlight.getSecondFlight();
        return new Leg.Builder()
                .departureAirport(interconnectedFlight.getMiddleAirport())
                .arrivalAirport(expectedLeg.getArrivalAirport())
                .departureDateTime(
                        LocalDateTime.of(interconnectedFlight.getSecondFlight().getDate(), secondFlight.getDepartureTime()))
                .arrivalDateTime(
                        LocalDateTime.of(interconnectedFlight.getSecondFlight().getDate(), secondFlight.getArrivalTime()))
                .build();
    }
}
