import java.util.HashMap;
import java.util.Map;

public class Template {

    private Map<String, String> variables;
    private TemplateParser parser;

    public Template(String template) {
        this.parser = new TemplateParser(template);
        this.variables = new HashMap<>();
    }

    public String evaluate() {
        final StringBuilder builder = new StringBuilder();

        parser.parseSegments().forEach(segment -> {
            if (isVariable(segment)) {
                String name = segment.substring(2, segment.length() - 1);
                String value = variables.get(name);

                if (null == value) {
                    throw new MissingValueException();
                }

                builder.append(value);
            } else {
                builder.append(segment);
            }
        });

        return builder.toString();
    }

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    public void set(String name, String value) {
        variables.put(name, value);
    }
}
