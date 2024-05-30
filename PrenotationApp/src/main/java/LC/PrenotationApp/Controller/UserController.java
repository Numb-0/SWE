package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ReservationService;
import LC.PrenotationApp.BuisnessLogic.ReviewManager;
import LC.PrenotationApp.Entities.CustomUserDetails;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/dashboard-reservation-add")
    @ResponseBody
    public ResponseEntity<String> addReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservation.setUser(userDetailsService.getAuthenticatedUserData());
        reservationService.saveReservation(reservation);
        return ResponseEntity.ok("Reservation added successfully");
    }

}
