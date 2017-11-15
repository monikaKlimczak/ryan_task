package ryan.task2.interconnectingflights.api;

import java.util.List;

public class Timetable {

    private int month;

    private List<Day> days;

    public Timetable(int month, List<Day> days) {
        this.month = month;
        this.days = days;
    }

    public int getMonth() {
        return month;
    }

    public List<Day> getDays() {
        return days;
    }
}
