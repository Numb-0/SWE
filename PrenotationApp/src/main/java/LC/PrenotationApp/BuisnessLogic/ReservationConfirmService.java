package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
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
    @Autowired
    private ItemDao itemDao;

    public Reservation getReservationById(long id) throws ChangeSetPersister.NotFoundException {
        Reservation reservation = reservationDao.findById(id);
        if (reservation == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return reservation;
    }

    public void startReservation(Reservation reservation) {
        reservation.ReservationStart(LocalDate.now());
        reservationDao.save(reservation);
    }

}