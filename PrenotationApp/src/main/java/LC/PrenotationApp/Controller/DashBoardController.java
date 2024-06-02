package LC.PrenotationApp.Controller;


import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ItemService;

import LC.PrenotationApp.BuisnessLogic.ReservationService;
import LC.PrenotationApp.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashBoardController {
    @Autowired
    ItemService itemService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CustomUserDetailsService userDetailsService;

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
                    return "staff-dashboard";
            }
        }
        return "index";
    }
}