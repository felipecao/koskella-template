import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    private static final Pattern PATTERN = Pattern.compile("\\$\\{[^}]*\\}");

    private String template;

    public TemplateParser(String template) {
        this.template = template;
    }

    public List<String> parseSegments() {
        List<String> segments = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(template);
        Integer currentPositionInTemplate = 0;

        while (matcher.find()) {
            addPrecedingPlainText(segments, matcher, currentPositionInTemplate);
            addSegment(segments, matcher);
            currentPositionInTemplate = matcher.end();
        }

        addPlainTextTailSegment(segments, currentPositionInTemplate);
        addEmptyStringIfTemplateWasEmpty(segments);

        return segments;
    }

    private void addPrecedingPlainText(List<String> segments, Matcher matcher, Integer currentPosition) {
        if (currentPosition < matcher.start()) {
            segments.add(template.substring(currentPosition, matcher.start()));
        }
    }

    private void addSegment(List<String> segments, Matcher matcher) {
        segments.add(template.substring(matcher.start(), matcher.end()));
    }

    private void addPlainTextTailSegment(List<String> segments, Integer currentPositionInTemplate) {
        if (currentPositionInTemplate < template.length()) {
            segments.add(template.substring(currentPositionInTemplate));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
        if (segments.isEmpty()) {
            segments.add("");
        }
    }
}
