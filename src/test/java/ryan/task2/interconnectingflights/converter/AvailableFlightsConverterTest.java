package ryan.task2.interconnectingflights.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ryan.task2.interconnectingflights.api.AvailableFlight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailableFlightsConverterTest {

    @Mock
    private DefaultLegConverter legConverter;

    @InjectMocks
    private AvailableFlightsConverter converter;

    @Test
    public void returnZeroAvailableFlightsWhenEmptyFlightsTest() {
        //given
        List<FlightWithDate> directFlights = Collections.emptyList();
        List<InterconnectedFlightWithDates> interconnectedFlights = Collections.emptyList();
        Leg expectedLeg = new Leg.Builder().build();

        //when
        List<AvailableFlight> availableFlights =
                converter.convertFrom(directFlights, interconnectedFlights, expectedLeg);

        //then
        assertTrue(availableFlights.isEmpty());
    }

    @Test
    public void returnAvailableFlightsWhenNotEmptyFlightsTest() {
        //given
        List<FlightWithDate> directFlights = Collections.singletonList(mock(FlightWithDate.class));
        List<InterconnectedFlightWithDates> interconnectedFlights =
                Collections.singletonList(mock(InterconnectedFlightWithDates.class));
        Leg expectedLeg = mock(Leg.class);

        List<Leg> legsDirect = Collections.singletonList(mock(Leg.class));
        List<Leg> legsInterconnected = Arrays.asList(mock(Leg.class), mock(Leg.class));
        when(legConverter.toDirectLeg(eq(expectedLeg), any(FlightWithDate.class))).thenReturn(legsDirect.get(0));
        when(legConverter.toInterconnectedFirstLeg(eq(expectedLeg), any(InterconnectedFlightWithDates.class)))
                .thenReturn(legsInterconnected.get(0));
        when(legConverter.toInterconnectedSecondLeg(eq(expectedLeg), any(InterconnectedFlightWithDates.class)))
                .thenReturn(legsInterconnected.get(1));

        //when
        List<AvailableFlight> availableFlights =
                converter.convertFrom(directFlights, interconnectedFlights, expectedLeg);

        //then
        List<AvailableFlight> expectedAvailableFlights = Arrays.asList(
                new AvailableFlight.Builder().stops(0).legs(legsDirect).build(),
                new AvailableFlight.Builder().stops(1).legs(legsInterconnected).build()
        );
        assertEquals(expectedAvailableFlights, availableFlights);
    }

}