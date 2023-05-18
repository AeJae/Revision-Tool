package com.ajsf;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuestionBank qb = createQuestionBank("testQuestions");
        System.out.println(qb);
    }

    // Creates a question bank and populates it with questions from a passed text file.
    public static QuestionBank createQuestionBank(String filename) {
        QuestionBank qb = new QuestionBank();
        try {
            Scanner scanner = new Scanner(new File(filename + ".txt")).useDelimiter("&");
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