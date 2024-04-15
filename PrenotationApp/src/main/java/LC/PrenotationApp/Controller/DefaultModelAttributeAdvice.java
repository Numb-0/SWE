package LC.PrenotationApp.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// This class sets default attributes for all the controllers model
@ControllerAdvice
public class DefaultModelAttributeAdvice {

    @ModelAttribute("isIndexPage")
    public boolean isIndexPage() {
        return false; // Default value
    }
}