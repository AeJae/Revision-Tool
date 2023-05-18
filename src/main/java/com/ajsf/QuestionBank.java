package com.ajsf;

import java.util.ArrayList;

// Stores and manages an array of questions.
public class QuestionBank {
    private ArrayList<Question> questions = new ArrayList<>();

    public String toString() {
        StringBuilder s = new StringBuilder("\nBANK OF SIZE " + this.bankSize() + ":");
        int count = 0;
        for (Question q : questions) {
            s.append("\n\t0x").append(count).append(": ").append(q.getType()).append(": ").append(q.getTitle());
            count++;
        }
        return s.toString();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void deleteQuestion(Question q) {
        questions.remove(q);
    }

    public void deleteQuestion(int index) {
        questions.remove(index);
    }

    public int bankSize() {
        return questions.size();
    }
}
