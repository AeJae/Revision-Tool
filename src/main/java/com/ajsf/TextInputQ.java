package com.ajsf;

import static com.ajsf.Main.inArray;

import java.util.Scanner;

// Text Input Question
public class TextInputQ extends Question {
    // Attributes
    private final String[] correctEntries;

    // Constructor
    public TextInputQ(String source, String title, String correctEntries) {
        super(source, title);
        this.correctEntries = toArray(correctEntries);
    }

    // Question Output (toString)
    @Override
    public String toString() {
        return String.format("[From %s]\n%s", this.getSource(), this.getTitle());
    }

    // Accessors
    public String[] getCorrectEntries() {
        return correctEntries;
    }

    // Testing
    public static void main(String[] args) {
        TextInputQ q1 = new TextInputQ("Test Source", "This is a ____ question.", "[test;fake]");
        Scanner in = new Scanner(System.in);

        System.out.println(q1);
        String entry = in.nextLine();

        if (inArray(entry, q1.getCorrectEntries())) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }
    }
}
