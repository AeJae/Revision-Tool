//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

import java.util.ArrayList;

// Stores and manages an array of questions.
public class QuestionBank {
    // The question bank itself.
    private ArrayList<Question> questions = new ArrayList<>();

    // Outputs the entire bank.
    public String toString() {
        StringBuilder s = new StringBuilder("\nBANK OF SIZE " + this.bankSize() + ":");
        int question = 0;
        for (Question q : questions) {
            s.append("\n\t0x").append(question).append(": ").append(q.getType()).append(": ").append(q.getTitle());
            question++;
        }
        return s.toString();
    }

    // Returns a new, filtered version of this question bank based on the passed question source.
    public QuestionBank filter(String source) throws InvalidSourceException {
        int questionsFound = 0;
        // Create a new question bank to store the filtered questions.
        QuestionBank qb = new QuestionBank();

        // Add all questions with the wanted source to the new question bank.
        for (Question q : questions) {
            if (q.getSource().equalsIgnoreCase(source)) {
                qb.addQuestion(q);
                questionsFound++;
            }
        }

        // Check that the filter is valid.
        if (questionsFound == 0) {
            throw new InvalidSourceException(source);
        }

        return qb;
    }

    // Adds a question.
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // Deletes a question by the question itself.
    public void deleteQuestion(Question q) {
        questions.remove(q);
    }

    // Deletes a question by its index.
    public void deleteQuestion(int index) {
        questions.remove(index);
    }

    // Returns the question at the passed index.
    public Question getQuestion(int index) {
        return questions.get(index);
    }

    // Returns the number of questions in the bank.
    public int bankSize() {
        return questions.size();
    }
}
