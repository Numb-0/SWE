package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.Entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationConfirmService {
    @Autowired
    private ReservationDao reservationDao;

    public Reservation getReservationById(long id) throws ChangeSetPersister.NotFoundException {
        Reservation reservation = reservationDao.findById(id);
        if (reservation == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return reservation;
    }

    public void startReservation(Reservation reservation) {
        reservation.reservationStart(LocalDate.now());
        reservationDao.save(reservation);
    }

    public void closeReservation(Reservation reservation) {
        reservation.closeReservation();
    }
}