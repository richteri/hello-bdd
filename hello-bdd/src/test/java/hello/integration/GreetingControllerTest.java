/**
 * 
 */
package hello.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import hello.Application;
import net.thucydides.core.annotations.Issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author István
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=8080")
public class GreetingControllerTest {
    
    @Value("${local.server.port}")
    private int port;
    
    RestTemplate template = new TestRestTemplate();

    /**
     * Test method for {@link hello.controller.GreetingController#greeting(java.lang.String, org.springframework.ui.Model)}.
     */
    @Test @Issue("BDD-4")
    public void testGreeting() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"+port+"/greeting", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    /**
     * Test method for {@link hello.controller.GreetingController#form(hello.Person, java.lang.String)}.
     */
    @Test @Issue("BDD-2")
    public void testForm() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:"+port+"/form", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    /**
     * Test method for {@link hello.controller.GreetingController#checkPersonInfo(hello.Person, org.springframework.validation.BindingResult, org.springframework.web.servlet.mvc.support.RedirectAttributes)}.
     */
    @Test @Issue("BDD-2")
    public void testCheckPersonInfo() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Language", "en");
        
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.set("name", "");
        form.set("age", "");
        
        ResponseEntity<String> entity = template.exchange("http://localhost:"+port+"/form", HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(form, headers), String.class);

        assertThat(entity.getBody(), containsString("size must be between 2 and 30"));
        assertThat(entity.getBody(), containsString("may not be null"));
    }

}
