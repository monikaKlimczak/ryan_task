package ryan.task2.interconnectingflights.api.withdate;

public class InterconnectedFlightWithDates {

    private FlightWithDate firstFlight;

    private FlightWithDate secondFlight;

    private String middleAirport;

    public InterconnectedFlightWithDates(FlightWithDate firstFlight, FlightWithDate secondFlight, String middleAirport) {
        this.firstFlight = firstFlight;
        this.secondFlight = secondFlight;
        this.middleAirport = middleAirport;
    }

    public FlightWithDate getFirstFlight() {
        return firstFlight;
    }

    public FlightWithDate getSecondFlight() {
        return secondFlight;
    }

    public String getMiddleAirport() {
        return middleAirport;
    }
}
