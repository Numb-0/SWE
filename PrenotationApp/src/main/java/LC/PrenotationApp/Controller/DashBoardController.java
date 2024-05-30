package LC.PrenotationApp.Controller;


import LC.PrenotationApp.BuisnessLogic.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashBoardController {
    @Autowired
    ItemService itemService;

    @GetMapping("/dashboard")
    public String showDashBoard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String userRole = authority.getAuthority();
            switch (userRole) {
                case "ROLE_USER":
                    return "user-dashboard";
                case "ROLE_MANAGER":
                    return "manager-dashboard";
                case "ROLE_STAFF":
                    return "staff-dashboard";
            }
        }
        return "index";
    }
}