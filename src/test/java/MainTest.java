import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.ajsf.*;

// Tests
public class MainTest {
    @Test
    @DisplayName("True or False Question Test")
    public void testTrueFalseResult() {
        Question q1 = new TrueFalseQ("Test Source", "This is a test.", true);
        boolean outcome = q1.isCorrect("true") &! q1.isCorrect("false");
        Assertions.assertTrue(outcome);
    }

    @Test
    @DisplayName("Multiple Choice Question Test")
    public void testMultipleChoiceResult() {
        Question q2 = new MultiChoiceQ("Test Source", "Test Options:", "[A;B;C;D]", "[1;4]");
        boolean part1 = q2.isCorrect("1");
        boolean part2 = q2.isCorrect("4");
        boolean part3 = q2.isCorrect("invalid");
        boolean outcome = part1 && part2 &! part3;
        Assertions.assertTrue(outcome);
    }

    @Test
    @DisplayName("Text Input Question Test")
    public void testTextInputResult() {
        Question q3 = new TextInputQ("Test Source", "This is a ____.", "[test;fake]");
        boolean part1 = q3.isCorrect("test");
        boolean part2 = q3.isCorrect("fake");
        boolean part3 = q3.isCorrect("invalid");
        boolean outcome = part1 && part2 &! part3;
        Assertions.assertTrue(outcome);
    }

}