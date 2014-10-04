package hello.controller;

import hello.domain.Person;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GreetingController {

    /**
     * Default controller 
     * @param name the name to greet
     * @param m the model
     * @return view name
     */
    
    @RequestMapping(value={"", "/", "greeting"})
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model m) {
        m.addAttribute("name", name);
        return "greeting";
    }

    /**
     * Form controller GET
     * @param person the form backing object
     */
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public void form(Person person, @ModelAttribute("message") String message) {

    }
    
    /**
     * Handle form POSTs
     * @param person the validated form backing object
     * @param bindingResult validation result
     * @param redirect the redirect attributes for displaying messages
     * @return view name
     */
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            // Displaying error messages
            return "form";
        }
        // Adding flash message
        redirect.addFlashAttribute("message", "Successfully created a new person");
        
        // Redirecting to empty form
        return "redirect:/form";
    }
}
