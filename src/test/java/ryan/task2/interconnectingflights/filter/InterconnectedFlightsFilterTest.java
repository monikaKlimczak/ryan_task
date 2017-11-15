package ryan.task2.interconnectingflights.filter;

import org.junit.Test;
import ryan.task2.interconnectingflights.api.Flight;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightsWithDates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterconnectedFlightsFilterTest {

    private InterconnectedFlightsFilter filter = new InterconnectedFlightsFilter();

    @Test
    public void filterByTimeCriteriaWhenFlightsListIsEmpty() {
        //given
        LocalDateTime departureDateTime = LocalDateTime.now();
        LocalDateTime arrivalDateTime = LocalDateTime.now();

        //when
        List<InterconnectedFlightWithDates> flights = filter.filterByTimeCriteria(
                departureDateTime, arrivalDateTime, new ArrayList<>());

        //then
        assertTrue(flights.isEmpty());
    }

    @Test
    public void filterByTimeCriteriaWithinOneDay() {
        //given
        Flight firstGoodFlight =
                new FlightWithDate("1", LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2017, 1, 1));
        Flight secondGoodFlight =
                new FlightWithDate("3", LocalTime.of(14, 0), LocalTime.of(15, 55), LocalDate.of(2017, 1, 1));
        LocalDateTime departureDateTime = LocalDateTime.of(2017, 1, 1, 10, 0);
        LocalDateTime arrivalDateTime = LocalDateTime.of(2017, 1, 1, 16, 0);
        List<InterconnectedFlightsWithDates > interconnectedFlightsList = Arrays.asList(getFlights());

        //when
        List<InterconnectedFlightWithDates> flights = filter.filterByTimeCriteria(
                departureDateTime, arrivalDateTime, interconnectedFlightsList);

        //then
        assertEquals(1, flights.size());
        assertEquals("AAA" , flights.get(0).getMiddleAirport());
        assertEquals(firstGoodFlight, flights.get(0).getFirstFlight());
        assertEquals(secondGoodFlight, flights.get(0).getSecondFlight());
    }

    private InterconnectedFlightsWithDates getFlights() {
        List<FlightWithDate> flightsDepartures = Arrays.asList(
                new FlightWithDate("1", LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2017, 1, 1)),
                new FlightWithDate("2", LocalTime.of(10, 0), LocalTime.of(16, 10), LocalDate.of(2017, 1, 1)));
        List<FlightWithDate> flightsArrivals = Arrays.asList(
                new FlightWithDate("3", LocalTime.of(14, 0), LocalTime.of(15, 55), LocalDate.of(2017, 1, 1)),
                new FlightWithDate("4", LocalTime.of(12, 30), LocalTime.of(15, 55), LocalDate.of(2017, 1, 1)),
                new FlightWithDate("5", LocalTime.of(14, 30), LocalTime.of(15, 55), LocalDate.of(2017, 1, 2))
                );
        InterconnectedFlightsWithDates flightsWithDates = new InterconnectedFlightsWithDates(
                flightsDepartures, flightsArrivals, "AAA");
        return flightsWithDates;
    }

}