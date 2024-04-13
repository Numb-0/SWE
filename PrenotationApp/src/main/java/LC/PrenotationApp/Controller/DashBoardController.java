package LC.PrenotationApp.Controller;


import LC.PrenotationApp.Entities.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/dashboard")
    public String showDashBoard(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String userRole = authority.getAuthority();
            if (userRole.equals("ROLE_USER")) {
               return "user-dashboard";
            } else if (userRole.equals("ROLE_ADMIN")) {
                return "admin-dashboard";
            }
        }

        return "dashboard";
    }
}