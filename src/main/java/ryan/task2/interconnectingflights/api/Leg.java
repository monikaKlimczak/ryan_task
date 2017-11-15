package ryan.task2.interconnectingflights.api;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Leg {

    @Size(min = 3, max = 3)
    private String departureAirport;

    @Size(min = 3, max = 3)
    private String arrivalAirport;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    public Leg(Builder builder) {
        this.departureAirport = builder.departureAirport;
        this.arrivalAirport = builder.arrivalAirport;
        this.departureDateTime = builder.departureDateTime;
        this.arrivalDateTime = builder.arrivalDateTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public static class Builder {

        private String departureAirport;
        private String arrivalAirport;
        private LocalDateTime departureDateTime;
        private LocalDateTime arrivalDateTime;

        public Builder departureAirport(String departureAirport) {
            this.departureAirport = departureAirport;
            return this;
        }

        public Builder arrivalAirport(String arrivalAirport) {
            this.arrivalAirport = arrivalAirport;
            return this;
        }

        public Builder departureDateTime(LocalDateTime departureDateTime) {
            this.departureDateTime = departureDateTime;
            return this;
        }

        public Builder arrivalDateTime(LocalDateTime arrivalDateTime) {
            this.arrivalDateTime = arrivalDateTime;
            return this;
        }

        public Leg build() {
            return new Leg(this);
        }
    }
}
