package com.ajsf;

// Multiple Choice Question
public class MultiChoiceQ extends Question {
    // Attributes
    private final String[] choices;
    private final String[] correctChoices;


    // Constructor
    public MultiChoiceQ(String source, String title, String choices, String correctChoices) {
        super(source, title, "Multiple Choice");
        this.choices = toArray(choices);
        this.correctChoices = toArray(correctChoices);
    }


    // Question Output (toString)
    @Override
    public String toString() {
        int count = 1;
        StringBuilder q = new StringBuilder(String.format("\n[From %s]\n%s\n", this.getSource(), this.getTitle()));
        for (String s : choices) {
            q.append(count).append(". ").append(s).append("\n");
            count++;
        }
        q.append("\nEnter choice:");
        return q.toString();
    }

    // Check Answer
    @Override
    public Boolean isCorrect(String entry) {
        return inArray(entry, this.getAnswer());
    }


    // Accessors
    public String[] getChoices() {
        return choices;
    }

    public String[] getAnswer() {
        return correctChoices;
    }
}
