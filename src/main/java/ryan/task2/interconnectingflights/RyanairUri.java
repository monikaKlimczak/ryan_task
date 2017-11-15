package ryan.task2.interconnectingflights;

public enum RyanairUri {

    ROUTES("https://api.ryanair.com/core/3/routes/"),
    TIMETABLE("https://api.ryanair.com/timetable/3/schedules/%s/%s/years/%s/months/%s");

    private final String uri;

    RyanairUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
