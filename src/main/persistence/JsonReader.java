package persistence;


import model.Food;
import model.History;
import model.Mood;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Referenced code from JsonSerializationDemo
// Represents a reader that reads history from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public History read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses history from JSON object and returns it
    private History parseHistory(JSONObject jsonObject) {
        History history = new History();
        addFoods(history, jsonObject);
        return history;
    }

    // MODIFIES: history
    // EFFECTS: parses foods from JSON object and adds them to history
    private void addFoods(History history, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food");
        for (Object json : jsonArray) {
            JSONObject nextHistory = (JSONObject) json;
            addFood(history, nextHistory);
        }
    }

    // MODIFIES: history
    // EFFECTS: parses food name to history and sets default mood to neutral
    private void addFood(History history, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Mood neutral = new Mood("Chiiir", "Kiwi is chill");
        Food food = new Food(name, neutral);
        history.addToHistory(food);
    }

}
