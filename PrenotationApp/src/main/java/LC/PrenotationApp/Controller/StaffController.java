package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ReservationConfirmService;
import LC.PrenotationApp.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StaffController {

    @Autowired
    ReservationConfirmService reservationConfirmService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @ModelAttribute("user_reservations")
    public List<Reservation> userReservations() {
        return new ArrayList<Reservation>();
    }

    @GetMapping("/staff-dashboard")
    public String showStaffDashboard() {
        return "staff-dashboard";
    }

    @PostMapping("/dashboard-get-user-reservations")
    public String getUserReservations(Model model, @RequestParam(name = "searched_user", required = false) String searched_user) {
        model.addAttribute("user_reservations", reservationConfirmService.getReservations(userDetailsService.getUserByUsername(searched_user)));
        return "staff-dashboard";
    }

    @PostMapping("dashboard-activate-reservation/{id}")
    public String activateReservation(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        reservationConfirmService.startReservation(reservationConfirmService.getReservationById(id));
        return "staff-dashboard";
    }

    @PostMapping("dashboard-close-reservation/{id}")
    public String closeReservation(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        reservationConfirmService.closeReservation(reservationConfirmService.getReservationById(id));
        return "staff-dashboard";
    }
}
