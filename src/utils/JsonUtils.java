package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Droid.Droid;
import BattleLog.BattleLog;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new Gson();

    // Сохранение списка дроидов в файл JSON
    public static void saveDroidsToFile(List<Droid> droids, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(droids, writer);
        }
    }

    // Загрузка списка дроидов из файла JSON
    public static List<Droid> loadDroidsFromFile(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            Type droidListType = new TypeToken<List<Droid>>() {}.getType();
            return gson.fromJson(reader, droidListType);
        }
    }

    public static void saveBattleLogToFile(BattleLog log, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(log, writer);
        }
    }

}
