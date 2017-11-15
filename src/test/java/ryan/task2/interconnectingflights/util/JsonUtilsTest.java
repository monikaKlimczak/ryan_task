package ryan.task2.interconnectingflights.util;

import org.junit.Test;
import ryan.task2.interconnectingflights.api.Route;
import ryan.task2.interconnectingflights.api.Timetable;

import java.util.List;

import static org.junit.Assert.*;

public class JsonUtilsTest {

    @Test
    public void convertToRoutes() throws Exception {
        //when
        List<Route> routes = new JsonUtils().convertToRoutes(getRoutesJson());

        //then
        assertEquals(2, routes.size());
        Route route1 = routes.get(0);
        assertEquals("LUZ", route1.getAirportFrom());
        assertEquals("STN", route1.getAirportTo());

        Route route2 = routes.get(1);
        assertEquals("CHQ", route2.getAirportFrom());
        assertEquals("SKG", route2.getAirportTo());
    }

    @Test
    public void convertToTimeTable() throws Exception {
        Timetable timetable = new JsonUtils().convertToTimeTable(getTimetableJson());

        assertEquals(6, timetable.getMonth());
        assertEquals(2, timetable.getDays().size());
    }

    private String getRoutesJson() {
        return "[\n" +
                "        {\n" +
                "        \"airportFrom\":\"LUZ\",\n" +
                "        \"airportTo\":\"STN\",\n" +
                "        \"newRoute\":false,\n" +
                "        \"seasonalRoute\":false\n" +
                "        },\n" +
                "        {\n" +
                "        \"airportFrom\":\"CHQ\",\n" +
                "        \"airportTo\":\"SKG\",\n" +
                "        \"newRoute\":false,\n" +
                "        \"seasonalRoute\":false\n" +
                "        }\n" +
                "        ]";
    }

    private String getTimetableJson() {
        return "{\n" +
                "        \"month\":6,\n" +
                "        \"days\":[\n" +
                "        {\n" +
                "        \"day\":1,\n" +
                "        \"flights\":[\n" +
                "        {\n" +
                "        \"number\":\"1926\",\n" +
                "        \"departureTime\":\"17:50\",\n" +
                "        \"arrivalTime\":\"21:25\"\n" +
                "        }]\n" +
                "        },\n" +
                "        {\n" +
                "        \"day\":2,\n" +
                "        \"flights\":[\n" +
                "        {\n" +
                "        \"number\":\"1926\",\n" +
                "        \"departureTime\":\"19:30\",\n" +
                "        \"arrivalTime\":\"23:05\"\n" +
                "        }]\n" +
                "        }\n" +
                "        ]\n" +
                "        }\n";
    }

}