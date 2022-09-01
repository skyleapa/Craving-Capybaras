package persistence;

import model.Food;
import model.History;
import model.Mood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    Mood happy;
    Mood sad;
    Mood hungry;

    Food watermelon;
    Food libraryBook;
    Food freshLinen;

    @BeforeEach
    public void setUp() {
        happy = new Mood("Chirrrrrrrrrrrp!", "Kiwi seems happy!");
        sad = new Mood("Blurpp....", "Kiwi seems dejected");
        hungry = new Mood("Muuurrpp!!", "Kiwi's stomach is growling");

        watermelon = new Food("Watermelon", happy);
        libraryBook = new Food("Library Book", sad);
        freshLinen = new Food("Fresh Linen", hungry);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            History history = new History();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHistory() {
        try {
            History history = new History();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHistory.json");
            history = reader.read();
            assertEquals(0, history.getHistory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHistory() {
        try {
            History history = new History();
            history.addToHistory(watermelon);
            history.addToHistory(libraryBook);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHistory.json");
            history = reader.read();
            List<Food> foods = history.getHistory();
            assertEquals(2, foods.size());
            checkFood("Watermelon", foods.get(0));
            checkFood("Library Book", foods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}