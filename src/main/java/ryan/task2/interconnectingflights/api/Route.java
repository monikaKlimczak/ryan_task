package ryan.task2.interconnectingflights.api;

public class Route {

    private String airportFrom;
    private String airportTo;

    private Route(Builder builder) {
        this.airportFrom = builder.airportFrom;
        this.airportTo = builder.airportTo;
    }

    public String getAirportFrom() {
        return airportFrom;
    }

    public String getAirportTo() {
        return airportTo;
    }

    public static class Builder {

        private String airportFrom;
        private String airportTo;

        public Builder airportFrom(String airportFrom) {
            this.airportFrom = airportFrom;
            return this;
        }

        public Builder airportTo(String airportTo) {
            this.airportTo = airportTo;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
