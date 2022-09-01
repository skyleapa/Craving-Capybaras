package persistence;

import model.Food;
import model.History;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            History history = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHistory.json");
        try {
            History history = reader.read();
            assertEquals(0, history.getHistory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHistory.json");
        try {
            History history = reader.read();
            List<Food> foods = history.getHistory();
            assertEquals(4, foods.size());
            checkFood("Watermelon", foods.get(0));
            checkFood("Library Book", foods.get(1));
            checkFood("Bermuda Grass", foods.get(2));
            checkFood("Fresh Linen", foods.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}