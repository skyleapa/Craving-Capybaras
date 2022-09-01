package ui;

import model.Food;
import model.History;
import model.Mood;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// CapybaraLog acts as the JSON writer and reading to build and store the history, as well as creating the default
// moods and foods that will be used to interact with Kiwi.
public class CapybaraLog {
    private static final String JSON_STORE = "./data/history.json";
    private History history;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Mood happy;
    Mood sad;
    Mood hungry;

    Food watermelon;
    Food libraryBook;
    Food freshLinen;
    Food bermudaGrass;

    // EFFECTS: Constructor, adds all default mood and foods then allows user to begin talking to kiwi
    public CapybaraLog() {
        history = new History();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addMoodAndFood();
    }

    // MODIFIES: foods
    // EFFECTS: adds all moods and foods for kiwi
    public void addMoodAndFood() {
        happy = new Mood("Chirrrrrrrrrrrp!", "Kiwi seems happy!");
        sad = new Mood("Chhthhttttt....", "Kiwi seems dejected");
        hungry = new Mood("Puuuuurrr!!", "Kiwi's stomach is growling");

        watermelon = new Food("Watermelon", happy);
        libraryBook = new Food("Library Book", sad);
        bermudaGrass = new Food("Bermuda Grass", happy);
        freshLinen = new Food("Fresh Linen", hungry);
    }

    // getters
    public Food getWatermelon() {
        return watermelon;
    }

    public Food getBermudaGrass() {
        return bermudaGrass;
    }

    public Food getFreshLinen() {
        return freshLinen;
    }

    public Food getLibraryBook() {
        return libraryBook;
    }

    public History getHistory() {
        return history;
    }


    // Referenced code from JsonSerializationDemo
    // EFFECTS: saves the history to file
    public void saveHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            System.out.println("Saved food history to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced code from JsonSerializationDemo
    // MODIFIES: this, history
    // EFFECTS: loads history from file
    public void loadHistory() {
        try {
            history = jsonReader.read();
            System.out.println("Loaded history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
