package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ReservationDao extends CrudRepository<Reservation, Integer> {
    Reservation findById(long id);

    List<Reservation> findByUser(User user);

    //List<Reservation> findByExpiredAndUser(Boolean expired, User user);
}

