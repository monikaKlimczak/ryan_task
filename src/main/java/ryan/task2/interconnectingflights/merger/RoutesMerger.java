package ryan.task2.interconnectingflights.merger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.InterconnectedRoute;
import ryan.task2.interconnectingflights.api.Route;
import ryan.task2.interconnectingflights.filter.RoutesFilter;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoutesMerger {

    @Autowired
    private RoutesFilter routesFilter;

    public List<InterconnectedRoute> mergeToInterconnectedRoutes(List<Route> routes, String departureAirport,
                                                                String arrivalAirport) {
        List<Route> routesFilteredByDepartureAirport = routesFilter.filterByDepartureAirport(routes, departureAirport);
        List<Route> routesFilteredByArrivalAirport = routesFilter.filterByArrivalAirport(routes, arrivalAirport);

        List<InterconnectedRoute> interconnectedRoutes = new ArrayList<>();
        for (Route routeByDepartureAirport: routesFilteredByDepartureAirport) {
            for (Route routeByArrivalAirport: routesFilteredByArrivalAirport) {
                if (routeByDepartureAirport.getAirportTo().equals(routeByArrivalAirport.getAirportFrom())) {
                    interconnectedRoutes.add(new InterconnectedRoute(routeByDepartureAirport, routeByArrivalAirport));
                }
            }
        }
        return interconnectedRoutes;
    }
}
