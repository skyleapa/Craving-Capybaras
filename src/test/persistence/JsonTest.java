package persistence;


import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String name, Food food) {
        assertEquals(name, food.getName());
    }
}
