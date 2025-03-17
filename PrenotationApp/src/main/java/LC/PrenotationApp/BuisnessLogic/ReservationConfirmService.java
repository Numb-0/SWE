package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationConfirmService {
    @Autowired
    private ReservationDao reservationDao;

    public List<Reservation> getReservations(User user) {
        List<Reservation> reservations = reservationDao.findByUser(user);
        reservations.removeIf(r -> !r.getActive() && r.getExpired());
        return reservations;
    }

    public Reservation getReservationById(long id) throws ChangeSetPersister.NotFoundException {
        Reservation reservation = reservationDao.findById(id);
        if (reservation == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return reservation;
    }

    public void startReservation(Reservation reservation) {
        if (!reservation.getActive() && !reservation.getExpired()) { 
            reservation.reservationStart();
            reservationDao.save(reservation);
        }
    }

    public void closeReservation(Reservation reservation) {
        if (reservation.getExpired()) {
            reservation.closeReservation();
            reservationDao.save(reservation);
        }

    }
}