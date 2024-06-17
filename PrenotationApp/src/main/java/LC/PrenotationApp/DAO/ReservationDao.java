package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ReservationDao extends CrudRepository<Reservation, Integer> {
    Reservation findById(long id);

    @Query("SELECT i FROM Reservation i WHERE i.user = :user")
    List<Reservation> findReservationsByUser(@Param("user") User user);

    @Query("SELECT i FROM Reservation i WHERE i.expired = :expired AND i.user = :user")
    List<Reservation> findReservationByStateAndUser(@Param("expired") Boolean expired, @Param("user") User user);


}

