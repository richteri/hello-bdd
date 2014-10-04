package hello.acceptance;

import hello.steps.GreetingSteps;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

public class GreetingTest {

    @Steps
    GreetingSteps endUser;

    @Given("the user is on the home page")
    public void givenTheUserIsOnTheHomePage() {
        endUser.on_the_home_page();
    }
    
    @Given("the user called '$name' is on the home page")
    public void givenTheUserXIsOnTheHome(String name) {
        endUser.on_the_home_page_with_name(name);
    }

    @Then("they should see the greeting Hello, '$name'")
    public void thenTheyShouldSeeTheGreetingHello(String name) {
        endUser.should_see_greeting(name);
    }

}
