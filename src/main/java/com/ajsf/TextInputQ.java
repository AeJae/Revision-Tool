package com.ajsf;

// Text Input Question
public class TextInputQ extends Question {
    // Attributes
    private final String[] correctEntries;


    // Constructor
    public TextInputQ(String source, String title, String correctEntries) {
        super(source, title, "Text Input");
        this.correctEntries = toArray(correctEntries);
    }


    // Question Output (toString)
    @Override
    public String toString() {
        return String.format("[From %s]\n%s", this.getSource(), this.getTitle());
    }

    // Check Answer
    @Override
    public Boolean isCorrect(String entry) {
        return inArray(entry, this.getCorrectEntries());
    }


    // Accessors
    public String[] getCorrectEntries() {
        return correctEntries;
    }
}
