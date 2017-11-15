package ryan.task2.interconnectingflights.finder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.AvailableFlight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;
import ryan.task2.interconnectingflights.converter.AvailableFlightsConverter;

import java.util.List;

@Component
public class AvailableFlightsFinder {

    @Autowired
    private DirectFlightsFinder directFlightsFinder;

    @Autowired
    private InterconnectedFlightsFinder interconnectedFlightsFinder;

    @Autowired
    private AvailableFlightsConverter availableFlightsConverter;


    public List<AvailableFlight> find(final Leg expectedLeg) {
        List<FlightWithDate> directFlights = directFlightsFinder.find(expectedLeg);
        List<InterconnectedFlightWithDates> interconnectedFlights = interconnectedFlightsFinder.find(expectedLeg);

        return availableFlightsConverter.convertFrom(directFlights, interconnectedFlights, expectedLeg);
    }
}
