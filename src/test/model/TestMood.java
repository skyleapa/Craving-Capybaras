package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMood {
    Mood happy;
    Mood sad;
    Mood hungry;

    @BeforeEach
    public void setUp() {
        happy = new Mood("Chirrrrrrrrrrrp!", "Kiwi seems happy!");
        sad = new Mood("Blurpp....", "Kiwi seems dejected");
        hungry = new Mood("Muuurrpp!!", "Kiwi's stomach is growling");

    }

    @Test
    public void testGetMessage() {
        assertEquals("Chirrrrrrrrrrrp!", happy.getMessage());
    }

    @Test
    public void testGetNarration() {
        assertEquals("Kiwi seems dejected", sad.getNarration());
    }
}
