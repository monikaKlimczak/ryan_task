package ryan.task2.interconnectingflights.api.withdate;

import java.util.List;

public class InterconnectedFlightsWithDates {

    private List<FlightWithDate> flightsDepartures;

    private List<FlightWithDate> flightsArrivals;

    private String middleAirport;

    public InterconnectedFlightsWithDates(List<FlightWithDate> flightsDepartures, List<FlightWithDate> flightsArrivals,
                                          String middleAirport) {
        this.flightsDepartures = flightsDepartures;
        this.flightsArrivals = flightsArrivals;
        this.middleAirport = middleAirport;
    }

    public List<FlightWithDate> getFlightsDepartures() {
        return flightsDepartures;
    }

    public List<FlightWithDate> getFlightsArrivals() {
        return flightsArrivals;
    }

    public String getMiddleAirport() {
        return middleAirport;
    }
}
