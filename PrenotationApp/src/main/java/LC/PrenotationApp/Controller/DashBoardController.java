package LC.PrenotationApp.Controller;


import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashBoardController {
    @Autowired
    ItemService itemService;

    @GetMapping("/dashboard")
    public String showDashBoard(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String userRole = authority.getAuthority();
            if (userRole.equals("ROLE_USER")) {
               return "user-dashboard";
            } else if (userRole.equals("ROLE_ADMIN")) {
                model.addAttribute("book", new Item(Item.Type.book));
                return "admin-dashboard";
            }
            // TODO
        }
        return "dashboard";
    }


    @PostMapping("/dashboard-add")
    public ResponseEntity<String> addItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return ResponseEntity.ok("Book added successfully");
    }
}