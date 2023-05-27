//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

// Multiple Choice Question
public class MultiChoiceQ extends Question {
    // Attributes
    private final String[] choices;
    private final String[] correctChoices;


    // Constructor
    public MultiChoiceQ(String source, String title, String choices, String correctChoices) {
        super(source, title, "Multiple Choice");
        this.choices = toArray(choices);
        this.correctChoices = toArray(correctChoices);
    }


    // Question Output (toString)
    @Override
    public String toString() {
        int count = 1;
        StringBuilder q = new StringBuilder(String.format("[From %s] [%s]\n%s\n", this.getSource(), this.getType(),
                this.getTitle()));
        for (String s : choices) {
            q.append(count).append(". ").append(s).append("\n");
            count++;
        }
        q.append("\nEnter choice:");
        return q.toString();
    }

    // Question Output
    @Override
    public String getPresentable(boolean showSource) {
        int count = 1;
        StringBuilder q = new StringBuilder();
        // If the source should be shown, add it at the start.
        if (showSource) {
            q.append("[").append(this.getSource()).append("] ");
        }
        // Add the question type, title and options.
        q.append(String.format("[%s]\n%s\n", this.getType(), this.getTitle()));
        for (String s : choices) {
            q.append(count).append(". ").append(s).append("\n");
        }
        q.append("\nEnter choice:");
        return q.toString();
    }

    // Check Answer
    @Override
    public Boolean isCorrect(String entry) {
        return inArray(entry, this.getAnswer());
    }


    // Accessors
    public String[] getChoices() {
        return choices;
    }

    public String[] getAnswer() {
        return correctChoices;
    }
}
