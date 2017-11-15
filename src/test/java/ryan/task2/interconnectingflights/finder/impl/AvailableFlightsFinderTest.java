package ryan.task2.interconnectingflights.finder.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ryan.task2.interconnectingflights.api.AvailableFlight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;
import ryan.task2.interconnectingflights.converter.AvailableFlightsConverter;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailableFlightsFinderTest {

    @Mock
    private DirectFlightsFinder directFlightsFinder;

    @Mock
    private InterconnectedFlightsFinder interconnectedFlightsFinder;

    @Mock
    private AvailableFlightsConverter availableFlightsConverter;

    @InjectMocks
    private AvailableFlightsFinder availableFlightsFinder;

    @Test
    public void findAvailableFlightsTest() {
        //given
        Leg leg = mock(Leg.class);
        List<FlightWithDate> directFlights = mock(List.class);
        when(directFlightsFinder.find(leg)).thenReturn(directFlights);
        List<InterconnectedFlightWithDates> interconnectedFlights = mock(List.class);
        when(interconnectedFlightsFinder.find(leg)).thenReturn(interconnectedFlights);
        List<AvailableFlight> expectedAvailableFlights = mock(List.class);
        when(availableFlightsConverter.convertFrom(directFlights, interconnectedFlights, leg))
                .thenReturn(expectedAvailableFlights);

        //when
        List<AvailableFlight> availableFlights = availableFlightsFinder.find(leg);

        //then
        assertEquals(expectedAvailableFlights, availableFlights);
    }

}