package ryan.task2.interconnectingflights.api;

import java.util.List;

public class AvailableFlight {

    private int stops;
    private List<Leg> legs;

    public AvailableFlight(Builder builder) {
        this.stops = builder.stops;
        this.legs = builder.legs;
    }

    public int getStops() {
        return stops;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    @Override
    public boolean equals(Object o) {
        AvailableFlight availableFlight = (AvailableFlight) o;
        return availableFlight.getStops() == this.getStops()
                && availableFlight.getLegs().equals(this.getLegs());
    }

    public static class Builder {

        private int stops;
        private List<Leg> legs;

        public Builder stops(int stops) {
            this.stops = stops;
            return this;
        }

        public Builder legs(List<Leg> legs) {
            this.legs = legs;
            return this;
        }

        public AvailableFlight build() {
            return new AvailableFlight(this);
        }
    }
}
