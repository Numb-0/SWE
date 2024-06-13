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

import java.util.ArrayList;
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
        return reservationService.filterItems("","","");
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
        reservationService.saveReservation(new Reservation(userDetailsService.getAuthenticatedUserData(), reservation.getItem()));
        return ResponseEntity.ok("Reservation added successfully");
    }

    @PostMapping("/dashboard-reservation-filter")
    public String updateFilter(@RequestParam String genre, @RequestParam String author, @RequestParam String title, Model model) {
        model.addAttribute("filter_books", reservationService.filterItems(genre, author, title));
        return "user-dashboard";
    }

}
