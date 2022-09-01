package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHistory {
    Capybara kiwi;
    History history;

    Mood happy;
    Mood sad;
    Mood hungry;

    Food watermelon;
    Food libraryBook;
    Food freshLinen;

    @BeforeEach
    public void setUp() {
        kiwi = new Capybara();
        history = new History();

        happy = new Mood("Chirrrrrrrrrrrp!", "Kiwi seems happy!");
        sad = new Mood("Blurpp....", "Kiwi seems dejected");
        hungry = new Mood("Muuurrpp!!", "Kiwi's stomach is growling");

        watermelon = new Food("Watermelon", happy);
        libraryBook = new Food("Library Book", sad);
        freshLinen = new Food("Fresh Linen", hungry);
    }

    @Test
    public void testPrintHistory() {
        assertEquals("Kiwi hasn't eaten anything yet", history.printHistory());
        history.addToHistory(watermelon);
        assertEquals("Watermelon" + "\n", history.printHistory());

        history.addToHistory(libraryBook);
        assertEquals("Watermelon" + "\n" + "Library Book" + "\n", history.printHistory());

        history.addToHistory(freshLinen);
        assertEquals("Watermelon" + "\n" + "Library Book" + "\n" + "Fresh Linen" + "\n",
                history.printHistory());

    }

    @Test
    public void testAddToHistory() {
        assertEquals(0, history.getHistory().size());
        history.addToHistory(watermelon);

        assertEquals(1, history.getHistory().size());
        assertEquals(watermelon, history.getHistory().get(0));

        history.addToHistory(libraryBook);
        assertEquals(2, history.getHistory().size());
        assertEquals(libraryBook, history.getHistory().get(1));
    }
}
