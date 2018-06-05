import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemplateParserTest {

    @Test
    public void aTemplateWithAnEmptySegmentIsParsedAsOneEmptySegment() {
        List<String> segments = parse("");
        assertSegmentsAre(segments, "");
    }

    @Test
    public void aTemplateWithOneSegmentOfPlainTextIsParsedAsOneSegmentOfPlainText() {
        List<String> segments = parse("a");
        assertSegmentsAre(segments, "a");
    }

    @Test
    public void aTemplateWithOneSegmentOfPlainTextAndOneVariableSegmentIsParsedAsOnePlainTextAndOneVariable() {
        List<String> segments = parse("a ${b}");
        assertSegmentsAre(segments, "a ", "${b}");
    }

    @Test
    public void aTemplateWithOneSegmentOfPlainTextAndOneVariableSegmentWithSpaceIsParsedAsOnePlainTextAndOneVariable() {
        List<String> segments = parse("a ${ b }");
        assertSegmentsAre(segments, "a ", "${ b }");
    }

    @Test
    public void templateWithoutSpacesAndTwoVariables() {
        List<String> segments = parse("a:${b}");
        assertSegmentsAre(segments, "a:", "${b}");
    }

    @Test
    public void templateWithSpacesAndMultiplePlainTextsAndVariables() {
        List<String> segments = parse("a ${b}:c: ${d}");
        assertSegmentsAre(segments, "a ", "${b}", ":c: ", "${d}");
    }

    private List<String> parse(String template) {
        return new TemplateParser(template).parseSegments();
    }

    private void assertSegmentsAre(List<String> segments, String... expectedOutput) {
        assertEquals(Arrays.asList(expectedOutput), segments);
    }

}
