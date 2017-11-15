package ryan.task2.interconnectingflights.filter;

import org.junit.Test;
import ryan.task2.interconnectingflights.api.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultRoutesFilterTest {

    private RoutesFilter routesFilter = new RoutesFilter();

    @Test
    public void filterEmptyListByDepartureAirportTest() {
        //given
        List<Route> emptyList = new ArrayList<>();

        //when
        List<Route> filteredRoutes = routesFilter.filterByDepartureAirport(emptyList, "AAA");

        //then
        assertTrue(filteredRoutes.isEmpty());
    }

    @Test
    public void filterByDepartureAirportWhenNoRouteMeetsCriteriaTest() {
        //given
        Route route1 = new Route.Builder()
                .airportFrom("AAA")
                .airportTo("BBB")
                .build();
        Route route2 = new Route.Builder()
                .airportFrom("CCC")
                .airportTo("AAA")
                .build();

        List<Route> routes = Arrays.asList(route1, route2);

        //when
        List<Route> filteredRoutes = routesFilter.filterByDepartureAirport(routes, "BBB");

        //then
        assertTrue(filteredRoutes.isEmpty());

    }

    @Test
    public void filterByDepartureAirportWhenSomeRouteMeetsCriteriaTest() {
        //given
        Route route1 = new Route.Builder()
                .airportFrom("AAA")
                .airportTo("BBB")
                .build();
        Route route2 = new Route.Builder()
                .airportFrom("CCC")
                .airportTo("AAA")
                .build();

        List<Route> routes = Arrays.asList(route1, route2);

        //when
        List<Route> filteredRoutes = routesFilter.filterByDepartureAirport(routes, "AAA");

        //then
        assertEquals(1, filteredRoutes.size());
        assertEquals(route1, filteredRoutes.get(0));
    }

    @Test
    public void filterEmptyListByArrivalAirportTest() {
        //given
        List<Route> emptyList = new ArrayList<>();

        //when
        List<Route> filteredRoutes = routesFilter.filterByArrivalAirport(emptyList, "AAA");

        //then
        assertTrue(filteredRoutes.isEmpty());
    }

    @Test
    public void filterByArrivalAirportWhenNoRouteMeetsCriteriaTest() {
        //given
        Route route1 = new Route.Builder()
                .airportFrom("AAA")
                .airportTo("BBB")
                .build();
        Route route2 = new Route.Builder()
                .airportFrom("CCC")
                .airportTo("AAA")
                .build();

        List<Route> routes = Arrays.asList(route1, route2);

        //when
        List<Route> filteredRoutes = routesFilter.filterByArrivalAirport(routes, "CCC");

        //then
        assertTrue(filteredRoutes.isEmpty());

    }

    @Test
    public void filterByArrivalAirportWhenSomeRouteMeetsCriteriaTest() {
        //given
        Route route1 = new Route.Builder()
                .airportFrom("AAA")
                .airportTo("BBB")
                .build();
        Route route2 = new Route.Builder()
                .airportFrom("CCC")
                .airportTo("AAA")
                .build();

        List<Route> routes = Arrays.asList(route1, route2);

        //when
        List<Route> filteredRoutes = routesFilter.filterByArrivalAirport(routes, "AAA");

        //then
        assertEquals(1, filteredRoutes.size());
        assertEquals(route2, filteredRoutes.get(0));
    }
}