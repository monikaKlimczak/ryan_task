package ryan.task2.interconnectingflights.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.AvailableFlight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AvailableFlightsConverter {

    @Autowired
    private DefaultLegConverter legConverter;

    public List<AvailableFlight> convertFrom(List<FlightWithDate> directFlights,
                                             List<InterconnectedFlightWithDates> interconnectedFlights,
                                             Leg expectedLeg) {
        List<AvailableFlight> availableFlights = new ArrayList<>();
        availableFlights.addAll(convertFromDirect(directFlights, expectedLeg));
        availableFlights.addAll(convertFromInterconnected(interconnectedFlights, expectedLeg));
        return availableFlights;
    }

    private List<AvailableFlight> convertFromDirect(List<FlightWithDate> directFlights, Leg expectedLeg) {
        List<AvailableFlight> availableFlights = new ArrayList<>();

        for (FlightWithDate flight: directFlights) {
            availableFlights.add(new AvailableFlight.Builder()
                    .stops(0)
                    .legs(Collections.singletonList(legConverter.toDirectLeg(expectedLeg, flight)))
                    .build()
            );
        }
        return availableFlights;
    }

    private List<AvailableFlight> convertFromInterconnected(List<InterconnectedFlightWithDates> interconnectedFlights,
                                                            Leg expectedLeg) {
        List<AvailableFlight> availableFlights = new ArrayList<>();

        for (InterconnectedFlightWithDates interconnectedFlight: interconnectedFlights) {
            availableFlights.add(new AvailableFlight.Builder()
                    .stops(1)
                    .legs(Arrays.asList(
                            legConverter.toInterconnectedFirstLeg(expectedLeg, interconnectedFlight),
                            legConverter.toInterconnectedSecondLeg(expectedLeg, interconnectedFlight)))
                    .build()
            );
        }
        return availableFlights;
    }
}
