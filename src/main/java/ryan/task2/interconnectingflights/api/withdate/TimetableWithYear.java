package ryan.task2.interconnectingflights.api.withdate;

import ryan.task2.interconnectingflights.api.Day;
import ryan.task2.interconnectingflights.api.Timetable;

import java.util.List;

public class TimetableWithYear extends Timetable {

    private int year;

    public TimetableWithYear(int month, List<Day> days, int year) {
        super(month, days);
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
