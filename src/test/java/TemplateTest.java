import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemplateTest {

    private Template template;

    @After
    public void tearDown() {
        template = null;
    }

    @Test
    public void emptyTemplateEvaluatesToEmptyString() {
        template("").evaluatesTo("");
    }

    @Test
    public void evaluateTemplateWithOneVariable() {
        template("Hello, ${name}")
                .withVariable("name", "Reader")
                .evaluatesTo("Hello, Reader");
    }

    @Test
    public void evaluateTemplateWithOneVariableAndDifferentValue() {
        template("Hello, ${name}")
                .withVariable("name", "Rodrigo")
                .evaluatesTo("Hello, Rodrigo");
    }

    @Test
    public void evaluateTemplateWithTwoVariables() {
        template("${greeting}, ${name}")
                .withVariable("greeting", "Hi")
                .withVariable("name", "Reader")
                .evaluatesTo("Hi, Reader");
    }

    @Test(expected = MissingValueException.class)
    public void evaluateTemplateWithMissingVariable() {
        evaluateTemplate("Hello, ${name}");
    }

    @Test
    public void evaluateTemplateWithUnusedVariable() {
        template("Hello, ${name}")
                .withVariable("doesnotexist", "Hi")
                .withVariable("name", "Reader")
                .evaluatesTo("Hello, Reader");
    }

    @Test
    public void evaluateTemplateWithMarkup() {
        template("${one}, ${two}, ${three}")
                .withVariable("one", "1")
                .withVariable("two", "${foo}")
                .withVariable("three", "3")
                .evaluatesTo("1, ${foo}, 3");
    }

    private TemplateTest template(String templateText) {
        template = new Template(templateText);
        return this;
    }

    private TemplateTest withVariable(String name, String value) {
        template.set(name, value);
        return this;
    }

    private void evaluatesTo(String expectedOutput) {
        assertEquals(expectedOutput, template.evaluate());
    }

    private void evaluateTemplate(String templateText) {
        template = new Template(templateText);
        template.evaluate();
    }
}
