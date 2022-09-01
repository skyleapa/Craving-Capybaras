package model;

// Represents a mood, with a verbal reaction when a Capybara has this mood, as well as a narrated representation of
// what the mood means
public class Mood {
    private String message;
    private String narration;

    // EFFECTS: constructs a mood
    public Mood(String message, String narration) {
        this.message = message;
        this.narration = narration;
    }

    // getters
    public String getMessage() {
        return this.message;
    }

    public String getNarration() {
        return this.narration;
    }

}
