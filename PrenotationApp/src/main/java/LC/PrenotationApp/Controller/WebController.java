package LC.PrenotationApp.Controller;

import LC.PrenotationApp.Entities.Utente;
import LC.PrenotationApp.DAO.UtentiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    public UtentiDao utentiDao;

    @GetMapping({"/", "/index.html"})
    public String showIndex() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "register";
    }

    @PostMapping("/register")
    public String showRegisterSuccess(@ModelAttribute Utente utente, Model model) {
        model.addAttribute("utente", utente);
        utentiDao.save(utente);
        return "LoginSuccess";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "login";
    }

}
