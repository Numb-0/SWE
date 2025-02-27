package LC.PrenotationApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import LC.PrenotationApp.BuisnessLogic.ReservationService;

@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private ReservationService reservationService;

    // Configurazione del scheduler
    @Scheduled(cron = "0 0 0 * * ?") // Esegue ogni giorno a mezzanotte
    public void performDailyCheck() {
        reservationService.checkExpired();
    }
}