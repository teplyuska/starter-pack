package starter.pack.shared.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static Gson gson = null;

    private static Gson gson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }

        return gson;
    }

    public static String serialize(Object o) {
        return gson().toJson(o);
    }

    public static <T> T deSerialize(String s, Class<T> c) {
        return gson().fromJson(s, c);
    }
}
