package LC.PrenotationApp.Controller;


import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/dashboard")
    public String showDashBoard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Item> bookList = itemService.getBookItems();
        model.addAttribute("books", bookList);
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