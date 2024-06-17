package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ReservationConfirmService;
import LC.PrenotationApp.BuisnessLogic.ReservationService;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class StaffController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationConfirmService reservationConfirmService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @ModelAttribute("user_reservations")
    public List<Reservation> userReservations() {
        return new ArrayList<>();
    }

    @GetMapping("/staff-dashboard")
    public String showStaffDashboard(Model model) {
        return "staff-dashboard";
    }

    @PostMapping("/dashboard-get-user-reservations")
    public String getUserReservations(Model model, @RequestParam String searched_user) {
        model.addAttribute("user_reservations", reservationService.getReservations(userDetailsService.getUserByUsername(searched_user)));
        return "staff-dashboard";
    }

    @PostMapping("dashboard-activate-reservation/{id}")
    public String activateReservation(@PathVariable long id) throws ChangeSetPersister.NotFoundException {
        System.out.println("sdsada"+ id);
        reservationConfirmService.startReservation(reservationConfirmService.getReservationById(id));
        return "staff-dashboard";
    }
}
