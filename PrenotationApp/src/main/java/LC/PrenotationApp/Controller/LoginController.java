package LC.PrenotationApp.Controller;

import LC.PrenotationApp.Entities.User;
import LC.PrenotationApp.DAO.UserDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

@Controller
public class LoginController{
    @Autowired
    public UserDao userDao;

    // Nel GetMapping we show the page register and create an utente model
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // This chooses which page to show
    }

    // This shows the html page register_success and saves the user in the database
    // PostMapping!! (method="post")

    @PostMapping("/register")
    public String showRegisterSuccess(@Valid User user, BindingResult bindingResult, Model model) {
        // also needs to check is user already in db
        if(bindingResult.hasErrors()){
            return "register";
        }
        model.addAttribute("user", user);
        userDao.save(user);
        return "login_success";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String showLoginSuccess(@Valid User user, BindingResult bindingResult, Model model) {
        // get info and validate
        //  access
        if(bindingResult.hasErrors()){
            return "login";
        }
        model.addAttribute("user", user);
        return "login_success";
    }

}
