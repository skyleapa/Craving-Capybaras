package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCapybara {
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
    public void testGetMood() {
        kiwi.setMood(happy);
        assertEquals(happy, kiwi.getMood());
    }


    @Test
    public void testFeedCapybara() {
        assertEquals(0, history.getHistory().size());

        kiwi.feedCapybara(watermelon, history);
        assertEquals(watermelon, history.getHistory().get(0));
        assertEquals(happy, kiwi.getMood());

        kiwi.feedCapybara(watermelon, history);
        assertEquals(happy, kiwi.getMood());
        kiwi.feedCapybara(libraryBook, history);
        assertEquals(sad, kiwi.getMood());

        assertEquals("Watermelon", watermelon.getName());
        assertEquals("Library Book", libraryBook.getName());

        assertEquals(watermelon, history.getHistory().get(1));
        assertEquals(libraryBook, history.getHistory().get(2));
        assertEquals(3, history.getHistory().size());

    }

    @Test
    public void testCalorieCounter() {
        assertEquals("30", kiwi.calorieCounter("Watermelon"));
        assertEquals("20", kiwi.calorieCounter("Fresh Linen"));
        assertEquals("50", kiwi.calorieCounter("Library Book"));
        assertEquals("80", kiwi.calorieCounter("Bermuda Grass"));
        assertEquals("", kiwi.calorieCounter("non valid food"));
    }



}
