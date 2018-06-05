import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemplateTest {

    @Test
    public void emptyTemplateEvaluatesToEmptyString() {
        Template template = new Template("");

        assertEquals("", template.evaluate());
    }

    @Test
    public void evaluateTemplateWithOneVariable() {
        Template template = new Template("Hello, ${name}");

        template.set("name", "Reader");

        assertEquals("Hello, Reader", template.evaluate());
    }

    @Test
    public void evaluateTemplateWithOneVariableAndDifferentValue() {
        Template template = new Template("Hello, ${name}");

        template.set("name", "Rodrigo");

        assertEquals("Hello, Rodrigo", template.evaluate());
    }

    @Test
    public void evaluateTemplateWithTwoVariables() {
        Template template = new Template("${greeting}, ${name}");

        template.set("greeting", "Hi");
        template.set("name", "Reader");

        assertEquals("Hi, Reader", template.evaluate());
    }

}
