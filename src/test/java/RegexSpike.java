import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class RegexSpike {

    @Test
    public void testMatcherStartEndOneVariable() {
        String input = "a ${ b }";
        Pattern pattern = Pattern.compile("\\$\\{.*\\}");
        Matcher matcher = pattern.matcher(input);

        assertEquals(true, matcher.find());
        assertEquals(2, matcher.start());
        assertEquals(8, matcher.end());
    }

    @Test
    public void testMatcherStartEndMultipleVariables() {
        String input = "a ${ b } c ${d}";
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(input);

        assertEquals(true, matcher.find());
        assertEquals(2, matcher.start());
        assertEquals(8, matcher.end());

        assertEquals(true, matcher.find());
        assertEquals(11, matcher.start());
        assertEquals(15, matcher.end());
    }

}
