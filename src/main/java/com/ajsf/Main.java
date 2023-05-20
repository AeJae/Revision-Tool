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
    // Used so question filters can be removed.
    public static QuestionBank originalQuestionBank = null;


    // Main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome! Use -quit to exit at any time, and once answering questions -answer to view " +
                "question answers, or -filter to enter the question filtering dialogue.");

        // Keep asking for a file until a valid one is given or the user asks to quit.
        while (true) {
            System.out.println("\nPlease enter the name of the question bank:");
            String input = in.nextLine();

            // Check if the user wants to quit.
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
        boolean statsReset = false;
        QuestionBank qb = createQuestionBank(file);
        originalQuestionBank = qb;
        System.out.println(qb + "\n");

        // Infinitely test randomly chosen questions.
        while (true) {
            boolean repeat = true;
            int rand = ThreadLocalRandom.current().nextInt(0, qb.bankSize());
            Question question = qb.getQuestion(rand);

            // Keep asking the same question where appropriate. Unless told to repeat, the next question is chosen.
            while (repeat) {
                repeat = false;
                System.out.println("\n" + question);
                String input = in.nextLine();

                switch (input) {
                    // Check if the user is wanting to quit.
                    case "-quit" -> {
                        String statsOut = String.format("%nMark: %.1f%%. Total Correct: %d. Total Answered: %d.",
                                ((double) correctQs / (double) totalQs) * 100, correctQs, totalQs);
                        if (statsReset) {
                            statsOut = statsOut + " [Statistics Reset During Session]";
                        }
                        System.out.println(statsOut);
                        System.exit(101);
                    }
                    // Or if they want the answers.
                    case "-answer" -> System.out.println(Arrays.toString(question.getAnswer()));
                    // Or if they want to skip.
                    case "-skip" -> System.out.println("<Skipped>");
                    // Or if they want to view their stats.
                    case "-stats" -> {
                        String statsOut = String.format("%nMark: %.1f%%. Total Correct: %d. Total Answered: %d.",
                                ((double) correctQs / (double) totalQs) * 100, correctQs, totalQs);
                        if (statsReset) {
                            statsOut = statsOut + " [Statistics Reset During Session]";
                        }
                        System.out.println(statsOut);
                        repeat = true;
                    }
                    // Or if they want to apply a filter.
                    case "-filter" -> qb = applyFilterTo(qb, false);
                    // Or if they want to reset filters.
                    case "-reset filters" -> qb = applyFilterTo(qb, true);
                    // Or if they want to reset statistics.
                    case "-reset stats" -> {
                        statsReset = true;
                        totalQs = correctQs = 0;
                        System.out.println("<Reset Statistics>");
                    }
                    // Otherwise they've entered an answer, which we should check is correct.
                    default -> {
                        totalQs++;
                        if (question.isCorrect(input)) {
                            System.out.println("Correct!");
                            correctQs++;
                        } else {
                            System.out.println("Incorrect.");
                            repeat = true;
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
                        if (data[3].equalsIgnoreCase("true")) {
                            ans = true;
                        } else if (data[3].equalsIgnoreCase("false")) {
                            ans = false;
                        } else {
                            throw new RuntimeException(String.format("0x%d - Invalid true or false question answer: " +
                                    "'%s'. Full question:%n%s", count, data[3], lastQ));
                        }
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
        // Triggered if the user has incorrectly formatted their question bank. Used for diagnostics.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("0x" + count + " - Invalid question: " + lastQ);
            e.printStackTrace();
            System.exit(-1);
        }
        return qb;
    }


    // Applies a user-defined filter to served questions.
    public static QuestionBank applyFilterTo(QuestionBank unfiltered, boolean quickReset) {
        // Used only for the '-reset filters' command.
        if (quickReset) {
            System.out.println("<Reset Filters>");
            return originalQuestionBank;
        }

        // Initial variables.
        boolean finished = false;
        Scanner in = new Scanner(System.in);
        QuestionBank newQuestionBank = unfiltered;

        // Interface start for the filter dialogue.
        System.out.printf("%n%sFilter Dialogue%s%nTo go back, use '-back'.%nTo remove all filters use '-reset'." +
                        "%nEnter the question source you'd like:%n", "-".repeat(17), "-".repeat(17));

        // Keep asking for a filter until a valid source is chosen or the user wants to go back.
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
            // Check if the user wants to quit.
            if (chosenSource.equalsIgnoreCase("-quit")) {
                System.exit(102);
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