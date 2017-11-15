package ryan.task2.interconnectingflights.merger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ryan.task2.interconnectingflights.api.InterconnectedRoute;
import ryan.task2.interconnectingflights.api.Route;
import ryan.task2.interconnectingflights.filter.RoutesFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoutesMergerTest {

    @Mock
    private RoutesFilter routesFilter;

    @InjectMocks
    private RoutesMerger routesMerger;

    @Test
    public void mergeToInterconnectedRoutesWhenFilteredRoutesEmpty() {
        //given
        List<Route> routes = mock(List.class);
        String departureAirport = "ABC";
        String arrivalAirport = "DEF";
        when(routesFilter.filterByDepartureAirport(routes, departureAirport)).thenReturn(new ArrayList<>());
        when(routesFilter.filterByArrivalAirport(routes, arrivalAirport)).thenReturn(new ArrayList<>());

        //when
        List<InterconnectedRoute> interconnectedRoutes =
                routesMerger.mergeToInterconnectedRoutes(routes, departureAirport, arrivalAirport);

        //then
        assertTrue(interconnectedRoutes.isEmpty());
    }

    @Test
    public void mergeToInterconnectedRoutesWhenFilteredDepartureRoutesEmpty() {
        //given
        List<Route> routes = mock(List.class);
        String departureAirport = "ABC";
        String arrivalAirport = "DEF";
        when(routesFilter.filterByDepartureAirport(routes, departureAirport)).thenReturn(new ArrayList<>());
        when(routesFilter.filterByArrivalAirport(routes, arrivalAirport)).thenReturn(Arrays.asList(mock(Route.class)));

        //when
        List<InterconnectedRoute> interconnectedRoutes =
                routesMerger.mergeToInterconnectedRoutes(routes, departureAirport, arrivalAirport);

        //then
        assertTrue(interconnectedRoutes.isEmpty());
    }

    @Test
    public void mergeToInterconnectedRoutesWhenFilteredArrivalRoutesEmpty() {
        //given
        List<Route> routes = mock(List.class);
        String departureAirport = "ABC";
        String arrivalAirport = "DEF";
        when(routesFilter.filterByDepartureAirport(routes, departureAirport)).thenReturn(Arrays.asList(mock(Route.class)));
        when(routesFilter.filterByArrivalAirport(routes, arrivalAirport)).thenReturn(new ArrayList<>());

        //when
        List<InterconnectedRoute> interconnectedRoutes =
                routesMerger.mergeToInterconnectedRoutes(routes, departureAirport, arrivalAirport);

        //then
        assertTrue(interconnectedRoutes.isEmpty());
    }

    @Test
    public void mergeToInterconnectedRoutesWhenFilteredRoutesNotEmpty() {
        //given
        List<Route> routes = mock(List.class);
        String departureAirport = "ABC";
        String arrivalAirport = "DEF";
        Route route = new Route.Builder().airportTo("AAA").build();
        Route route2 = new Route.Builder().airportFrom("AAA").build();
        Route route3 = new Route.Builder().airportFrom("BBB").build();
        when(routesFilter.filterByDepartureAirport(routes, departureAirport)).thenReturn(Arrays.asList(route));
        when(routesFilter.filterByArrivalAirport(routes, arrivalAirport)).thenReturn(Arrays.asList(route2, route3));

        //when
        List<InterconnectedRoute> interconnectedRoutes =
                routesMerger.mergeToInterconnectedRoutes(routes, departureAirport, arrivalAirport);

        //then
        assertEquals(1, interconnectedRoutes.size());
        assertEquals(route, interconnectedRoutes.get(0).getFirstRoute());
        assertEquals(route2, interconnectedRoutes.get(0).getSecondRoute());
    }
}