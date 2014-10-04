package hello.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://localhost:8080/")
@NamedUrls({
        @NamedUrl(name = "greet.name", url = "http://localhost:8080/?name={1}")
})
public class GreetingPage extends PageObject {
    @FindBy(id = "greeting")
    private WebElementFacade greeting;

    public String getGreeting() {
        return greeting.getTextValue();
    }
}
