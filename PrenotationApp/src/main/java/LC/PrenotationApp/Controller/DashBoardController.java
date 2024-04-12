package LC.PrenotationApp.Controller;


import LC.PrenotationApp.Entities.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/dashboard")
    public String showDashBoard(Model model){
        return "dashboard";
    }
}