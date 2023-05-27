//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

// Text Input Question
public class TextInputQ extends Question {
    // Attributes
    private final String[] correctEntries;


    // Constructor
    public TextInputQ(String source, String title, String correctEntries) {
        super(source, title, "Text Input");
        this.correctEntries = toArray(correctEntries);
    }


    // Question Output (toString)
    @Override
    public String toString() {
        return String.format("[From %s] [%s]\n%s", this.getSource(), this.getType(), this.getTitle());
    }

    // Question Output
    @Override
    public String getPresentable(boolean showSource) {
        String out = "";
        // If the source should be shown, add it at the start.
        if (showSource) {
            out += "[From" + this.getSource() + "] ";
        }
        // Add the question type, and the question itself.
        out += String.format("[%s]\n%s", this.getType(), this.getTitle());
        return out;
    }

    // Check Answer
    @Override
    public Boolean isCorrect(String entry) {
        return inArray(entry, this.getAnswer());
    }


    // Accessors
    public String[] getAnswer() {
        return correctEntries;
    }
}
