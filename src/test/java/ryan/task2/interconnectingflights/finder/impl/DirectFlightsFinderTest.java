package ryan.task2.interconnectingflights.finder.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import ryan.task2.interconnectingflights.util.JsonUtils;
import ryan.task2.interconnectingflights.api.Day;
import ryan.task2.interconnectingflights.api.Flight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.Timetable;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DirectFlightsFinderTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private JsonUtils jsonUtils;

    @InjectMocks
    private DirectFlightsFinder flightsFinder;

    @Test
    public void findFlightsWithinOneDayTest() {
        //given
        Flight goodFlight = new Flight("1", LocalTime.of(10, 0), LocalTime.of(12, 0));
        Flight tooLateArrivalFlight = new Flight("2", LocalTime.of(11, 0), LocalTime.of(13, 30));
        Flight tooEarlyDepartureFlight = new Flight("3", LocalTime.of(8, 0), LocalTime.of(11, 30));
        Flight wrongDayFlight = new Flight("4", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Day day1 = new Day(1, Arrays.asList(goodFlight, tooLateArrivalFlight, tooEarlyDepartureFlight));
        Day day2 = new Day(2, Arrays.asList(wrongDayFlight));

        Timetable timetable = new Timetable(2, Arrays.asList(day1, day2));
        when(jsonUtils.convertToTimeTable(anyString())).thenReturn(timetable);

        Leg expectedLeg = getLeg();

        //when
        List<FlightWithDate> foundFlights = flightsFinder.find(expectedLeg);

        //then
        assertEquals(1, foundFlights.size());
        assertEquals(goodFlight, foundFlights.get(0));
    }

    @Test
    public void findFlightsWithinMoreThanOneDayTest() {
        //given
        Flight goodFlight = new Flight("1", LocalTime.of(22, 0), LocalTime.of(23, 30));
        Flight goodFlight2 = new Flight("2", LocalTime.of(10, 0), LocalTime.of(14, 0));
        Flight goodFlight3 = new Flight("3", LocalTime.of(1, 30), LocalTime.of(4, 30));
        Flight tooEarlyDepartureFlight = new Flight("4", LocalTime.of(21, 0), LocalTime.of(23, 0));
        Flight tooLateArrivalFlight = new Flight("5", LocalTime.of(2, 0), LocalTime.of(7, 0));

        Day day1 = new Day(1, Arrays.asList(goodFlight, tooEarlyDepartureFlight));
        Day day2 = new Day(2, Arrays.asList(goodFlight2));
        Day day3 = new Day(3, Arrays.asList(goodFlight3, tooLateArrivalFlight));

        Timetable timetable = new Timetable(2, Arrays.asList(day1, day2, day3));
        when(jsonUtils.convertToTimeTable(anyString())).thenReturn(timetable);

        Leg expectedLeg = getFewDaysLeg();

        //when
        List<FlightWithDate> flights = flightsFinder.find(expectedLeg);

        //then
        assertEquals(3, flights.size());
        assertTrue(flights.contains(goodFlight));
        assertTrue(flights.contains(goodFlight2));
        assertTrue(flights.contains(goodFlight3));
    }

    private Leg getLeg() {
        return new Leg.Builder()
                .departureAirport("ABC")
                .arrivalAirport("DEF")
                .departureDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 2, 1), LocalTime.of(9, 10)))
                .arrivalDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 2, 1), LocalTime.of(13, 0)))
                .build();
    }

    private Leg getFewDaysLeg() {
        return new Leg.Builder()
                .departureAirport("ABC")
                .arrivalAirport("DEF")
                .departureDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 2, 1), LocalTime.of(22, 0)))
                .arrivalDateTime(
                        LocalDateTime.of(LocalDate.of(2017, 2, 3), LocalTime.of(6, 0)))
                .build();
    }
}