package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.Entities.Item;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    ItemService itemService;

    @ModelAttribute("book")
    public Item book (){
        return new Item(Item.Type.book);
    }

    @ModelAttribute("bookUpdate")
    public Item bookUpdate() {
        return new Item(Item.Type.book);
    }

    @ModelAttribute("books")
    public List<Item> getBooks() {
        return itemService.getBookItems();
    }

    @GetMapping("/manager-dashboard")
    public String showManagerDashboard() {
        return "manager-dashboard";
    }

    @PostMapping("/dashboard-book-add")
    @ResponseBody
    public ResponseEntity<String> addBook(@ModelAttribute("book") Item book) {
        itemService.saveItem(book);
        return ResponseEntity.ok("Book added successfully");
    }

    @PostMapping("/dashboard-book-edit/{id}")
    @ResponseBody
    public ResponseEntity<String> editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("bookUpdate") Item bookUpdate, BindingResult bindingResult) {
        // If there are errors pass them to the javascript
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid item data");
        }
        // Updates Item book Data
        itemService.updateBookItemById(id, bookUpdate);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/dashboard-book-delete/{id}")
    @ResponseBody
    public  ResponseEntity<String> removeBook(@PathVariable("id") Integer id) {
        try {
            // Delete the book
            itemService.removeBookItemById(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (Exception e) {
            // Handle the exception as you see fit
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting the book");
        }
    }
}
