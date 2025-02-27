package LC.PrenotationApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashBoardController {
    @GetMapping("/dashboard")
    public String showDashBoard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String userRole = authority.getAuthority();
            switch (userRole) {
                case "ROLE_USER":
                    return "redirect:/user-dashboard";
                case "ROLE_MANAGER":
                    return "redirect:/manager-dashboard";
                case "ROLE_STAFF":
                    return "redirect:/staff-dashboard";
            }
        }
        return "index";
    }
}