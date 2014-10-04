package hello.acceptance;

import hello.steps.FormSteps;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FormTest {

    @Before
    public void setupProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "hu-HU, hu");
        profile.setPreference("services.sync.prefs.sync.intl.accept_languages", true);
        profile.setAlwaysLoadNoFocusLib(true);
        profile.setEnableNativeEvents(true);
        Thucydides.useFirefoxProfile(profile);
    }

    @Steps
    FormSteps endUser;

    @Given("the user is on the form page")
    public void givenTheUserIsOnTheFormPage() {
        endUser.on_the_form_page();
    }

    @When("the user enters the name '$name' and age '$age' and submits the form")
    public void whenTheUserEntersName(String name, String age) {
        endUser.enters_name(name);
        endUser.enters_age(age);
        endUser.posts_form();
    }

    @Then("they should see the error message '$errorName' for name and '$errorAge' for age")
    public void thenTheyShouldSeeErrorMessages(String errorName, String errorAge) {
        endUser.should_see_name_error(errorName);
        endUser.should_see_age_error(errorAge);
    }

    @Then("they should see the success message '$message'")
    public void thenTheyShouldSeeSuccessMessage(String message) {
        endUser.should_see_success_message(message);
    }

}
