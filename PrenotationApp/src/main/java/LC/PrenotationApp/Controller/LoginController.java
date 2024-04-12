package LC.PrenotationApp.Controller;

import LC.PrenotationApp.Entities.User;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController{
    
//    @Autowired
//    private  CustomUserDetailsService userDetailsService;

    // Nel GetMapping we show the page register and create an utente model
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // This chooses which page to show
    }

    // This shows the html page register_success and saves the user in the database
    // PostMapping!! (method="post")

//    @PostMapping("/register")
//    public String showRegisterSuccess(@Valid User user, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            return "redirect:/register";
//        }
//        if(userDetailsService.registerNewUser(user)) {
//            return "redirect:/register_success";
//        }
//        return "redirect:/register";
//    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
