package ryan.task2.interconnectingflights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ryan.task2.interconnectingflights.api.AvailableFlight;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.finder.impl.AvailableFlightsFinder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightsController {

    @Autowired
    private AvailableFlightsFinder availableFlightsFinder;

    @RequestMapping(method = RequestMethod.GET, name = "/interconnections")
    public List<AvailableFlight> findAvailableFlights(
            @RequestParam(name = "departure") String departureAirport,
            @RequestParam(name = "arrival") String arrivalAirport,
            @RequestParam(name = "departureDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(name = "arrivalDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime) {

        Leg expectedLeg = new Leg.Builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(departureDateTime)
                .arrivalDateTime(arrivalDateTime)
                .build();
        return availableFlightsFinder.find(expectedLeg);
    }

}
