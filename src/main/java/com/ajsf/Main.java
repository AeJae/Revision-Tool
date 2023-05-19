//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    // Used so that question filters can be removed.
    public static QuestionBank originalQuestionBank = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome! Use -quit to exit at any time, and once answering questions -answer to view " +
                "question answers, or -filter to enter the question filtering dialogue.");

        // Keep asking for a file until a valid one is given or the user asks to quit.
        while (true) {
            System.out.println("\nPlease enter the name of the question bank:");
            String input = in.nextLine();

            if (input.equals("-quit")) {
                System.exit(100);
            }

            File file = new File(input + ".txt");
            if (file.exists()) {
                run(file);
                break;
            } else {
                System.out.println("File does not exist. Check the name and location of your question bank.");
            }
        }
    }


    // Runs a quiz based on the chosen question bank.
    public static void run(File file) {
        Scanner in = new Scanner(System.in);
        int totalQs = 0;
        int correctQs = 0;
        QuestionBank qb = createQuestionBank(file);
        originalQuestionBank = qb;
        System.out.println(qb + "\n");

        // Infinitely test randomly chosen questions.
        while (true) {
            boolean next = false;
            int rand = ThreadLocalRandom.current().nextInt(0, qb.bankSize());
            Question question = qb.getQuestion(rand);

            // Keep asking the same question until the user is correct, asks for the answer, or quits.
            while (!next) {
                System.out.println("\n" + question);
                String input = in.nextLine();

                switch (input) {
                    // Check if the user is wanting to quit.
                    case "-quit" -> {
                        System.out.printf("%nMark: %.1f%%. Total Correct: %d. Total Answered: %d.",
                                ((double) correctQs / (double) totalQs) * 100, correctQs, totalQs);
                        System.exit(101);
                    }
                    // Or if they want the answers.
                    case "-answer" -> {
                        System.out.println(Arrays.toString(question.getAnswer()));
                        next = true;
                    }
                    case "-stats" -> {
                        System.out.printf("%nMark: %.1f%%. Total Correct: %d. Total Answered: %d.%n",
                                ((double) correctQs / (double) totalQs) * 100, correctQs, totalQs);
                    }
                    // Or if they want to filter the questions.
                    case "-filter" -> {
                        qb = applyFilterTo(qb, false);
                        next = true;
                    }
                    // Or if they want to reset filters.
                    case "-reset filters" -> {
                        qb = applyFilterTo(qb, true);
                        next = true;
                    }
                    // Or if they want to reset statistics.
                    case "-reset stats" -> {
                        totalQs = correctQs = 0;
                        System.out.println("<Reset Statistics>");
                        next = true;
                    }
                    // Otherwise check if they're correct.
                    default -> {
                        totalQs++;
                        if (question.isCorrect(input)) {
                            System.out.println("Correct!");
                            correctQs++;
                            next = true;
                        } else {
                            System.out.println("Incorrect.");
                        }
                    }
                }
            }
        }
    }


    // Creates a question bank and populates it with questions from a passed text file.
    public static QuestionBank createQuestionBank(File file) {
        QuestionBank qb = new QuestionBank();
        String lastQ = "";
        int count = -1;
        try {
            // Add each question to the bank without loading the entire file into memory.
            Scanner scanner = new Scanner(file).useDelimiter("&");
            while (scanner.hasNext()) {
                count++;
                String[] data = scanner.next().split("\\$");
                lastQ = Arrays.toString(data);
                switch (data[0]) {
                    case "TF" -> {
                        boolean ans;
                        ans = data[3].equalsIgnoreCase("true");
                        qb.addQuestion(new TrueFalseQ(data[1], data[2], ans));
                    }
                    case "Text" -> qb.addQuestion(new TextInputQ(data[1], data[2], data[3]));
                    case "Multi" -> qb.addQuestion(new MultiChoiceQ(data[1], data[2], data[3], data[4]));
                    default -> throw new RuntimeException(String.format("0x%d - Invalid question: '%s'.",
                            count, lastQ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("0x" + count + " - Invalid question: " + lastQ);
            e.printStackTrace();
            System.exit(-1);
        }
        return qb;
    }


    // Applies a user-defined filter to served questions.
    public static QuestionBank applyFilterTo(QuestionBank unfiltered, boolean quickReset) {
        if (quickReset) {
            System.out.println("<Reset Filters>");
            return originalQuestionBank;
        }

        boolean finished = false;
        Scanner in = new Scanner(System.in);
        QuestionBank newQuestionBank = unfiltered;

        // Filter dialogue interface start.
        System.out.printf("%n%sFilter Dialogue%s%nTo go back, use '-back'.%nTo remove all filters use '-reset'." +
                        "%nEnter the question source you'd like:%n", "-".repeat(17), "-".repeat(17));

        // Run until a valid source is chosen or the user wants to go back.
        while (!finished) {
            String chosenSource = in.nextLine();

            // Check if the user wants to go back.
            if (chosenSource.equalsIgnoreCase("-back")) {
                System.out.println("Current filters were retained.\n" + "-".repeat(49));
                break;
            }
            // Check if the user wants to reset filters.
            if (chosenSource.equalsIgnoreCase("-reset")) {
                System.out.println("Removed all filters.\n" + "-".repeat(49));
                newQuestionBank = originalQuestionBank;
                break;
            }

            // Otherwise apply the filter, given there are matching questions.
            try {
                newQuestionBank = unfiltered.filter(chosenSource);
                System.out.println("Filter applied successfully.");
                System.out.println("-".repeat(49));
                finished = true;
            } catch (InvalidSourceException e) {
                System.out.println("Invalid filter, please try again:");
            }
        }

        return newQuestionBank;
    }
}