package LC.PrenotationApp.Controller;

import LC.PrenotationApp.Entities.User;
import LC.PrenotationApp.DAO.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        if(bindingResult.hasErrors()){
            return "register";
        }
        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser != null) {
            // User already exists, return to register page with error
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
            return "register";
        }
        model.addAttribute("user", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "register_success";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // User does not exist or password does not match, return to login page with error
            bindingResult.rejectValue("username", "error.user", "Invalid username or password");
            return "login";
        }
        // Password matches, login successful
        return "login_success";
    }

}
