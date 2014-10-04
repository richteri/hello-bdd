package hello.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://localhost:8080/form")
public class FormPage extends PageObject {

    @FindBy(name="name")
    private WebElementFacade nameField;

    @FindBy(name="age")
    private WebElementFacade ageField;

    @FindBy(id="nameError")
    private WebElementFacade nameError;

    @FindBy(id="ageError")
    private WebElementFacade ageError;
    
    @FindBy(id="submit")
    private WebElementFacade submitButton;

    @FindBy(id="message")
    private WebElementFacade message;

    public void enter_name(String s) {
        nameField.type(s);
    }

    public void enter_age(String s) {
        ageField.type(s);
    }
    
    public void submit() {
        submitButton.click();
    }

    public String getNameError() {
        return nameError.getTextValue();     
    }
    
    public String getAgeError() {
        return ageError.getTextValue();     
    }
    
    public String getMessage() {
        return message.getTextValue();
    }
}