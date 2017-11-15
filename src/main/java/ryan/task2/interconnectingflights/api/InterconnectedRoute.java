package ryan.task2.interconnectingflights.api;

public class InterconnectedRoute {

    private Route firstRoute;

    private Route secondRoute;

    public InterconnectedRoute(Route firstRoute, Route secondRoute) {
        this.firstRoute = firstRoute;
        this.secondRoute = secondRoute;
    }

    public Route getFirstRoute() {
        return firstRoute;
    }

    public Route getSecondRoute() {
        return secondRoute;
    }
}
