package ryan.task2.interconnectingflights.finder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.*;
import ryan.task2.interconnectingflights.api.InterconnectedRoute;
import ryan.task2.interconnectingflights.api.Leg;
import ryan.task2.interconnectingflights.api.Route;
import ryan.task2.interconnectingflights.api.withdate.FlightWithDate;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightWithDates;
import ryan.task2.interconnectingflights.api.withdate.InterconnectedFlightsWithDates;
import ryan.task2.interconnectingflights.api.withdate.TimetableWithYear;
import ryan.task2.interconnectingflights.filter.InterconnectedFlightsFilter;
import ryan.task2.interconnectingflights.finder.AbstractFlightsFinder;
import ryan.task2.interconnectingflights.merger.RoutesMerger;
import ryan.task2.interconnectingflights.util.JsonUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterconnectedFlightsFinder extends AbstractFlightsFinder {

    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
    private RoutesMerger routesMerger;

    @Autowired
    private InterconnectedFlightsFilter interconnectedFlightsFilter;

    public List<InterconnectedFlightWithDates> find(Leg expectedLeg) {
        String routesResponse = restTemplate.getForObject(RyanairUri.ROUTES.getUri(), String.class);
        List<Route> routes = jsonUtils.convertToRoutes(routesResponse);

        List<InterconnectedRoute> interconnectedRoutes =
                routesMerger.mergeToInterconnectedRoutes(routes, expectedLeg.getDepartureAirport(),
                expectedLeg.getArrivalAirport());

        List<InterconnectedFlightsWithDates> interconnectedFlightsList =
                getInterconnectedFlightsList(expectedLeg.getDepartureDateTime(), expectedLeg.getArrivalDateTime(),
                interconnectedRoutes);

        return interconnectedFlightsFilter.filterByTimeCriteria(expectedLeg.getDepartureDateTime(),
                expectedLeg.getArrivalDateTime(), interconnectedFlightsList);
    }

    private List<InterconnectedFlightsWithDates> getInterconnectedFlightsList(
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
            List<InterconnectedRoute> interconnectedRoutes) {
        List<InterconnectedFlightsWithDates> interconnectedFlightsList = new ArrayList<>();
        for (InterconnectedRoute interconnectedRoute: interconnectedRoutes) {
            TimetableWithYear timetableDepartures =
                    getTimetableForFlights(interconnectedRoute.getFirstRoute().getAirportFrom(),
                    interconnectedRoute.getFirstRoute().getAirportTo(), departureDateTime);
            TimetableWithYear timetableArrivals =
                    getTimetableForFlights(interconnectedRoute.getSecondRoute().getAirportFrom(),
                    interconnectedRoute.getSecondRoute().getAirportTo(), arrivalDateTime);

            List<FlightWithDate> flightsDepartures =
                    getFlightsForDays(timetableDepartures, departureDateTime.getDayOfMonth(),
                    arrivalDateTime.getDayOfMonth());
            List<FlightWithDate> flightsArrivals =
                    getFlightsForDays(timetableArrivals, departureDateTime.getDayOfMonth(),
                    arrivalDateTime.getDayOfMonth());

            interconnectedFlightsList.add(
                    new InterconnectedFlightsWithDates(flightsDepartures, flightsArrivals,
                            interconnectedRoute.getFirstRoute().getAirportTo()));
        }
        return interconnectedFlightsList;
    }
}