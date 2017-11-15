package ryan.task2.interconnectingflights.api;

import java.time.LocalTime;

public class Flight {

    private String number;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    public Flight(String number, LocalTime departureTime, LocalTime arrivalTime) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getNumber() {
        return number;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        Flight flight = (Flight) o;
        return this.number.equals(flight.getNumber());
    }

}
