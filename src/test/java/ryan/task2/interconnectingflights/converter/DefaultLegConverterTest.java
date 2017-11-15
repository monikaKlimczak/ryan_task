package ryan.task2.interconnectingflights.converter;

import org.junit.Test;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class DefaultLegConverterTest {

    private DefaultLegConverter defaultLegConverter = new DefaultLegConverter();

    @Test
    public void toDirectLegTest() {
        //given
        Leg leg = getLeg();
        FlightWithDate flight = new FlightWithDate("1", LocalTime.of(1, 0), LocalTime.of(2, 0), LocalDate.of(2017, 1, 1));

        //when
        Leg directLeg = defaultLegConverter.toDirectLeg(leg, flight);

        //then
        assertEquals("ABC", directLeg.getDepartureAirport());
        assertEquals("DEF", directLeg.getArrivalAirport());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(1, 0)), directLeg.getDepartureDateTime());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(2, 0)), directLeg.getArrivalDateTime());
    }

    @Test
    public void toInterconnectedFirstLegTest() {
        //given
        Leg leg = getLeg();
        InterconnectedFlightWithDates flight = getInterconnectedFlight();

        //when
        Leg directLeg = defaultLegConverter.toInterconnectedFirstLeg(leg, flight);

        //then
        assertEquals("ABC", directLeg.getDepartureAirport());
        assertEquals("BBB", directLeg.getArrivalAirport());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(1, 0)), directLeg.getDepartureDateTime());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(2, 0)), directLeg.getArrivalDateTime());
    }

    @Test
    public void toInterconnectedSecondLegTest() {
        //given
        Leg leg = getLeg();
        InterconnectedFlightWithDates flight = getInterconnectedFlight();

        //when
        Leg directLeg = defaultLegConverter.toInterconnectedSecondLeg(leg, flight);

        //then
        assertEquals("BBB", directLeg.getDepartureAirport());
        assertEquals("DEF", directLeg.getArrivalAirport());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(2, 30)), directLeg.getDepartureDateTime());
        assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(4, 10)), directLeg.getArrivalDateTime());
    }

    private Leg getLeg() {
        return new Leg.Builder()
                .departureAirport("ABC")
                .arrivalAirport("DEF")
                .departureDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(1, 10)))
                .arrivalDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.of(2, 20)))
                .build();
    }

    private InterconnectedFlightWithDates getInterconnectedFlight() {
        FlightWithDate firstFlight =
                new FlightWithDate("1", LocalTime.of(1, 0), LocalTime.of(2, 0), LocalDate.of(2017, 1, 1));
        FlightWithDate secondFlight =
                new FlightWithDate("2", LocalTime.of(2, 30), LocalTime.of(4, 10), LocalDate.of(2017, 1, 1));
        return new InterconnectedFlightWithDates(firstFlight, secondFlight, "BBB");
    }
}