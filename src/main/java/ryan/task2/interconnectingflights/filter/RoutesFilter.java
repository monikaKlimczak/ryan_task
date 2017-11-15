package ryan.task2.interconnectingflights.filter;

import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.Route;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoutesFilter {

    public List<Route> filterByDepartureAirport(final List<Route> routes, final String departureAirport) {
        return routes.stream()
                .filter(route -> route.getAirportFrom().equals(departureAirport))
                .collect(Collectors.toList());
    }

    public List<Route> filterByArrivalAirport(final List<Route> routes, final String arrivalAirport) {
        return routes.stream()
                .filter(route -> route.getAirportTo().equals(arrivalAirport))
                .collect(Collectors.toList());
    }
}
