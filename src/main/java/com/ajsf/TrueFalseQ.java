//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

public class TrueFalseQ extends Question {
    // Attributes
    private final boolean answer;


    // Constructor
    public TrueFalseQ(String source, String title, boolean answer) {
        super(source, title, "True or False");
        this.answer = answer;
    }


    // Question Output (toString)
    @Override
    public String toString() {
        return String.format("[From %s] [%s]\n%s", this.getSource(), this.getType(), this.getTitle());
    }


    // Check Answer
    public Boolean isCorrect(String entry) {
        boolean userAnswer;
        if (entry.equalsIgnoreCase("true")) {
            userAnswer = true;
        } else if (entry.equalsIgnoreCase("false")) {
            userAnswer = false;
        } else {
            System.out.println("Answer should be either true or false.");
            return false;
        }
        return userAnswer == this.answer;
    }


    // Accessors
    public String[] getAnswer() {
        String[] ans = new String[1];
        if (answer) {
            ans[0] = "True";
        } else {
            ans[0] = "False";
        }
        return ans;
    }
}
