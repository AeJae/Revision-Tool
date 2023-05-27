//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

public abstract class Question {
    // Attributes
    private final String type;
    private final String source;
    private final String title;


    // Constructor
    public Question(String source, String title, String type) {
        this.type = type;
        this.source = source;
        this.title = title;
    }


    // Misc Methods
    protected static String[] toArray(String string) {
        return string.substring(1, string.length()-1).split(";");
    }

    // Question Output (toString)
    public abstract String toString();

    // Question Output
    public abstract String getPresentable(boolean showSource);

    // Check Answer
    public abstract Boolean isCorrect(String entry);

    // Get Answer
    public abstract String[] getAnswer();

    // Checks if a string is in an array of this program's format
    protected static Boolean inArray(String input, String[] array) {
        for (String s : array) {
            if (s.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }


    // Accessors
    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
