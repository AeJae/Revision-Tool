package com.ajsf;

import java.util.ArrayList;

// Stores and manages an array of questions.
public class QuestionBank {
    private ArrayList<Question> questions = new ArrayList<>();

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
