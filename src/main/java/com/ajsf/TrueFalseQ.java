package com.ajsf;

public class TrueFalseQ extends Question {
    // Attributes
    private final boolean answer;


    // Constructor
    public TrueFalseQ(String source, String title, boolean answer) {
        super(source, title);
        this.answer = answer;
    }


    // Question Output (toString)
    @Override
    public String toString() {
        return String.format("[From %s]\n%s", this.getSource(), this.getTitle());
    }


    // Check Answer
    public Boolean isCorrect(String entry) {
        boolean userAnswer;
        if (entry.equalsIgnoreCase("true")) {
            userAnswer = true;
        } else if (entry.equalsIgnoreCase("false")) {
            userAnswer = false;
        } else {
            System.out.println("Answer should be either true or false.");
            return false;
        }
        return userAnswer == this.getAnswer();
    }


    // Accessors
    public Boolean getAnswer() {
        return answer;
    }
}
