package ryan.task2.interconnectingflights.api.withdate;

import ryan.task2.interconnectingflights.api.Flight;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightWithDate extends Flight {

    private LocalDate date;

    public FlightWithDate(String number, LocalTime departureTime, LocalTime arrivalTime, LocalDate date) {
        super(number, departureTime, arrivalTime);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
