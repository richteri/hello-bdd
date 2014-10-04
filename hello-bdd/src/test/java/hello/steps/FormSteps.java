package hello.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import hello.pages.FormPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class FormSteps extends ScenarioSteps {
    
    private static final long serialVersionUID = 1L;
    
    FormPage formPage;
    
    @Step
    public void on_the_form_page() {
        formPage.open();
    }

    @Step
    public void enters_name(String name) {
        formPage.enter_name(name);
    }
    
    @Step
    public void enters_age(String age) {
        formPage.enter_age(age);
    }

    @Step
    public void posts_form() {
        formPage.submit();
    }

    @Step
    public void should_see_name_error(String error) {
        assertThat(formPage.getNameError(), containsString(error));
    }

    @Step
    public void should_see_age_error(String error) {
        assertThat(formPage.getAgeError(), containsString(error));
    }
    
    @Step
    public void should_see_success_message(String message) {
        assertThat(formPage.getMessage(), containsString(message));
    }
    
}