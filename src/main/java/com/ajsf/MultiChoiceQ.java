package com.ajsf;

import java.util.Arrays;
import java.util.Scanner;

public class MultiChoiceQ extends Question {
    // Attributes
    private final String[] choices;
    private final String[] correctChoices;

    // Constructor
    public MultiChoiceQ(String source, String title, String choices, String correctChoices) {
        super(source, title);
        this.choices = toArray(choices);
        this.correctChoices = toArray(correctChoices);
    }

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

    // Accessors
    public String[] getChoices() {
        return choices;
    }

    public String[] getCorrectChoices() {
        return correctChoices;
    }

    // Testing
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MultiChoiceQ q1 = new MultiChoiceQ("Test Source", "Does this work?", "[Yes;No;Maybe]", "[3]");

        while (true) {
            System.out.println(q1);
            String entry = in.nextLine();
            if (inArray(entry, q1.getCorrectChoices())) {
                System.out.println("Correct");
                break;
            } else if (entry.equals("-exit")) {
                System.exit(1);
            } else if (entry.equals("-answer") || entry.equals("-answers")) {
                System.out.println(Arrays.toString(q1.getCorrectChoices()));
                break;
            } else {
                System.out.println("Incorrect");
            }
        }
    }
}
