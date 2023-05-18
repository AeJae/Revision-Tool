package com.ajsf;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome!");

        // Keep asking for a file until a valid one is given or the user asks to exit.
        while (true) {
            System.out.println("\nPlease enter the name of the test file:");
            String input = in.nextLine();

            if (input.equals("-exit")) {
                System.exit(100);
            }

            File file = new File(input + ".txt");
            if (file.exists()) {
                run(file);
                break;
            } else {
                System.out.println("File does not exist. Check the name and location of your file.");
            }
        }
    }


    // Runs a quiz based on the chosen question bank.
    public static void run(File file) {
        Scanner in = new Scanner(System.in);
        int totalQs = 0;
        int correctQs = 0;
        QuestionBank qb = createQuestionBank(file);
        System.out.println(qb + "\n");

        // Infinitely test randomly chosen questions.
        while (true) {
            int rand = ThreadLocalRandom.current().nextInt(0, qb.bankSize());
            Question question = qb.getQuestion(rand);

            // Keep asking the same question until the user is correct or asks for the answer.
            while (true) {
                System.out.println("\n" + question);
                String input = in.nextLine();
                // Check if the user is wanting to exit.
                if (input.equals("-exit")) {
                    System.out.printf("%nMark: %.1f%%. Total Correct: %d. Total Answered: %d.",
                            ((double) correctQs / (double) totalQs) * 100, correctQs, totalQs);
                    System.exit(101);
                // Or if they want the answers.
                } else if (input.equals("-answer")) {
                    System.out.println(Arrays.toString(question.getAnswer()));
                    break;
                // Otherwise check if they're correct.
                } else {
                    totalQs++;
                    if (question.isCorrect(input)) {
                        System.out.println("Correct!");
                        correctQs++;
                        break;
                    } else {
                        System.out.println("Incorrect.");
                    }
                }
            }
        }
    }


    // Creates a question bank and populates it with questions from a passed text file.
    public static QuestionBank createQuestionBank(File file) {
        QuestionBank qb = new QuestionBank();
        try {
            // Add each question to the bank without loading the entire file into memory.
            Scanner scanner = new Scanner(file).useDelimiter("&");
            while (scanner.hasNext()) {
                String[] data = scanner.next().split("\\$");
                switch (data[0]) {
                    case "TF" -> {
                        boolean ans;
                        ans = data[3].equalsIgnoreCase("true");
                        qb.addQuestion(new TrueFalseQ(data[1], data[2], ans));
                    }
                    case "Text" -> qb.addQuestion(new TextInputQ(data[1], data[2], data[3]));
                    case "Multi" -> qb.addQuestion(new MultiChoiceQ(data[1], data[2], data[3], data[4]));
                    default -> throw new RuntimeException("Invalid question type: " + data[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qb;
    }
}