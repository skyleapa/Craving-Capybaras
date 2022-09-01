package model;

// Represents a Capybara which has mood
public class Capybara {
    Mood mood;

    // EFFECTS: constructs a Capybara
    public Capybara() {
        this.mood = new Mood("Chhhiirpp", "Kiwi is hungry");
    }

    // getters
    public Mood getMood() {
        return this.mood;
    }

    // setters
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    // MODIFIES: Capybara, history
    // EFFECTS: feeds the food item to a Capybara, adding it to the history of food items eaten and affecting their mood
    public void feedCapybara(Food food, History history) {
        history.addToHistory(food);
        setMood(food.getMood());
        EventLog.getInstance().logEvent(new Event(food.getName() + " has been fed to Kiwi"));
    }

    // EFFECTS: gives the calorie amount of the food that the capybara has eaten
    public String calorieCounter(String foodName) {
        switch (foodName) {
            case "Watermelon":
                EventLog.getInstance().logEvent(new Event("Watermelon has 30 calories"));
                return "30";
            case "Library Book":
                EventLog.getInstance().logEvent(new Event("Library Book has 50 calories"));
                return "50";
            case "Bermuda Grass":
                EventLog.getInstance().logEvent(new Event("Bermuda Grass has 80 calories"));
                return "80";
            case "Fresh Linen":
                EventLog.getInstance().logEvent(new Event("Fresh Linen has 20 calories"));
                return "20";
            default:
                return "";
        }
    }

}
