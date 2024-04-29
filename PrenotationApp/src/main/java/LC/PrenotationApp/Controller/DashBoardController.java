package LC.PrenotationApp.Controller;


import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.User;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DashBoardController {
    @Autowired
    ItemService itemService;

    @GetMapping("/dashboard")
    public String showDashBoard(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Item> bookList = itemService.getBookItems();
        model.addAttribute("books", bookList);
        for (GrantedAuthority authority : auth.getAuthorities()) {
            String userRole = authority.getAuthority();
            if (userRole.equals("ROLE_USER")) {
               return "user-dashboard";
            } else if (userRole.equals("ROLE_ADMIN")) {
                return "admin-dashboard";
            }
            // TODO
        }
        return "dashboard";
    }

    @ModelAttribute("book")
    public Item book (){
        return new Item(Item.Type.book);
    }

    @ModelAttribute("bookUpdate")
    public Item bookUpdate() {
        return new Item(Item.Type.book);
    }

    // Calling this creates a newBook
    @PostMapping("/dashboard-book-add")
    @ResponseBody
    public ResponseEntity<String> addBook(@ModelAttribute("book") Item book) {
        itemService.saveItem(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @PostMapping("/dashboard-book-edit/{id}")
    @ResponseBody
    public ResponseEntity<String> editBook(@PathVariable("id") long id, @ModelAttribute("bookUpdate") Item bookUpdate) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            throw new IllegalArgumentException("Invalid item Id:" + id);
        }
        itemService.updateItemById(id, bookUpdate);
        return ResponseEntity.ok("Book updated successfully");
    }
}