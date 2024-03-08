package LC.PrenotationApp.Controller;

import LC.PrenotationApp.Entities.Utente;
import LC.PrenotationApp.DAO.UtentiDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

@Controller
public class LoginController{
    @Autowired
    public UtentiDao utentiDao;

    // Nel GetMapping we show the page register and create an utente model
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "register"; // This chooses which page to show
    }

    // This shows the html page register_success and saves the user in the database
    // PostMapping!! (method="post")

    @PostMapping("/register")
    public String showRegisterSuccess(@Valid Utente utente,BindingResult bindingResult, Model model) {
        // also needs to check is user already in db
        if(bindingResult.hasErrors()){
            return "register";
        }
        model.addAttribute("utente", utente);
        utentiDao.save(utente);
        return "login_success";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "login";
    }

    @PostMapping("/login")
    public String showLoginSuccess(@Valid Utente utente, BindingResult bindingResult, Model model) {
        // get info and validate
        //  access
        if(bindingResult.hasErrors()){
            return "login";
        }
        model.addAttribute("utente", utente);
        return "login_success";
    }

}
