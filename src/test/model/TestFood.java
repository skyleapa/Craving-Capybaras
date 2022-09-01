package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFood {
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


}