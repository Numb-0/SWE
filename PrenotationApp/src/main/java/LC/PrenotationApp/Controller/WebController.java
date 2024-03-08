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
    // This is not really required (for now) but I prefer to leave this here
    @GetMapping({"/", "/index.html"})
    public String showIndex() {
        return "index";
    }
}
