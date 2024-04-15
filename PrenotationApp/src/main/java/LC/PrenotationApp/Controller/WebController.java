package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    // This is not really required (for now) but I prefer to leave this here
    @GetMapping("/")
    public String showIndex(Model model) {
        // model here is used to add the isIndexPage bool that tells thymeleaf to make Register link visible
        model.addAttribute("isIndexPage", true);
        model.addAttribute("isUserLogged", userDetailsService.isUserLogged());
        return "index";
    }
}