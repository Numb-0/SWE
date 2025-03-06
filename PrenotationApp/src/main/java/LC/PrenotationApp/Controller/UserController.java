package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.BuisnessLogic.ReservationService;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ItemService itemService;

    @ModelAttribute("user_reservations")
    public List<Reservation> getUserReservations() {
        return reservationService.getReservations(userDetailsService.getAuthenticatedUserData());
    }

    @ModelAttribute("reservation")
    public Reservation createReservation() {
        return new Reservation();
    }

    @ModelAttribute("filter_books")
    public List<Item> getBooks() {
        // Get all possible books at page load
        return itemService.getReservableBookItems();
    }

    @GetMapping("/user-dashboard")
    public String showUserDashboard(Model model) {
        return "user-dashboard";
    }

    @PostMapping("/dashboard-reservation-add")
    @ResponseBody
    public ResponseEntity<String> addReservation(@ModelAttribute("reservation") Reservation reservation) {
        if (reservation.getItem().getState())
            return ResponseEntity.ok("Book is reserved");
        reservationService.createReservation(reservation, userDetailsService.getAuthenticatedUserData(), reservation.getItem());
        return ResponseEntity.ok("Reservation added successfully");
    }

    @PostMapping("/dashboard-reservation-filter")
    public String updateFilter(
    @RequestParam(name = "genre", required = false) String genre,
    @RequestParam(name = "author", required = false) String author,
    @RequestParam(name = "title", required = false) String title,
    Model model) {
        List<Item> filteredBooks = reservationService.filterItems(genre, author, title);
        System.out.println(filteredBooks);
        model.addAttribute("filter_books", filteredBooks);
        model.addAttribute("user_reservations", reservationService.getFilteredReservations(userDetailsService.getAuthenticatedUserData(), filteredBooks));
        return "user-dashboard";
    }

    @PostMapping("/dashboard-reservation-delete/{id}")
    public String removeReservation(@PathVariable("id") Integer id) {
        reservationService.removeReservation(id);
        return "redirect:/user-dashboard";
    }

}
