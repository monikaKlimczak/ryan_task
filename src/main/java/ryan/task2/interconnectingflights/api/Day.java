package ryan.task2.interconnectingflights.api;

import java.util.List;

public class Day {

    private int day;

    private List<Flight> flights;

    public Day(int day, List<Flight> flights) {
        this.day = day;
        this.flights = flights;
    }

    public int getDay() {
        return day;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
