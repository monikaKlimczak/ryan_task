package ryan.task2.interconnectingflights.converter;

import ryan.task2.interconnectingflights.api.Flight;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightsConverter {

    public List<FlightWithDate> convert(List<Flight> flights, LocalDate date) {
        List<FlightWithDate> flightsWithDate = new ArrayList<>();
        for (Flight flight: flights) {
            flightsWithDate.add(new FlightWithDate(flight.getNumber(), flight.getDepartureTime(),
                    flight.getArrivalTime(), date));
        }
        return flightsWithDate;
    }

}
