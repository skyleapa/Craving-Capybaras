package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food item with a name that will illicit a specific response from Kiwi after being consumed
public class Food implements Writable {
    private String name;
    private Mood mood;

    // EFFECTS: Constructs food object with a name and a mood that it induces from a capybara
    public Food(String name, Mood mood) {
        this.name = name;
        this.mood = mood;
    }

    // getters
    public Mood getMood() {
        return this.mood;
    }

    public String getName() {
        return this.name;
    }

    // Referenced code from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("mood", mood);
        return json;
    }


}
