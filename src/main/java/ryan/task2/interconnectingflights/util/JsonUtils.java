package ryan.task2.interconnectingflights.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.stereotype.Component;
import ryan.task2.interconnectingflights.api.Route;
import ryan.task2.interconnectingflights.api.Timetable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonUtils {

    public List<Route> convertToRoutes(String json) {
        return new Gson().fromJson(json, new TypeToken<ArrayList<Route>>() {}.getType());
    }

    public Timetable convertToTimeTable(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, getDeserializer()).create();
        return gson.fromJson(json, new TypeToken<Timetable>() {}.getType());
    }

    public JsonDeserializer<LocalTime> getDeserializer() {
        return (jsonElem, type, context) -> {

            if (jsonElem == null) {
                return null;
            }

            String localTime = jsonElem.getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(localTime, formatter);
        };
    }
}
