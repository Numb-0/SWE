package LC.PrenotationApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // This is not really required (for now) but I prefer to leave this here
    @GetMapping({"/", "/index.html"})
    public String showIndex() {
        return "index";
    }
}