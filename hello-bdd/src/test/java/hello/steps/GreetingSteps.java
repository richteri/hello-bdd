package hello.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import hello.pages.GreetingPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class GreetingSteps extends ScenarioSteps {
    
    private static final long serialVersionUID = 1L;
    
    GreetingPage greetingPage;
    
    @Step
    public void on_the_home_page() {
        greetingPage.open();
    }

    @Step
    public void on_the_home_page_with_name(String name) {
        greetingPage.open("greet.name", new String[] {name});
    }
    
    @Step
    public void should_see_greeting(String message) {
        assertThat(greetingPage.getGreeting(), containsString(message));
    }
    
}