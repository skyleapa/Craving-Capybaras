package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a history of food names that Kiwi has eaten. This history can be added onto or printed out
public class History implements Writable {
    ArrayList<Food> history;

    public History() {
        history = new ArrayList<>();
    }

    // getters
    public ArrayList<Food> getHistory() {
        return history;
    }

    // MODIFIES: this
    // EFFECTS: Adds the food item to Kiwi's list of foods eaten
    public void addToHistory(Food food) {
        history.add(food);
    }

    // EFFECTS: returns the food items Kiwi has eaten, or that kiwi hasn't eaten anything yet if history is empty
    public String printHistory() {
        String foodlist = "";
        if (history.isEmpty()) {
            return "Kiwi hasn't eaten anything yet";
        } else {
            for (Food f : history) {
                foodlist = foodlist + f.getName() + "\n";
            }
            return foodlist;
        }
    }

    // EFFECTS: adds the food to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food", foodToJson());
        return json;
    }

    // EFFECTS: returns food in history as a JSON array
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : history) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
