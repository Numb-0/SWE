package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private ItemDao itemDao;

    public void createReservation(User user, Item item) {
        item.toggleState();
        Reservation reservation = new Reservation(user, item);
        reservationDao.save(reservation);
    }

    public List<Reservation> getReservations(User user) {
        return reservationDao.findByUser(user);
    }

    public void saveReservation(Reservation reservation) {
        reservationDao.save(reservation);
    }

    public List<Reservation> getFilteredReservations(User user, List<Item> itemlist) {
        List<Reservation> reservations = getReservations(user);
        List<Reservation> filteredReservations = reservations.stream()
        .filter(reservation -> itemlist.contains(reservation.getItem()))
        .collect(Collectors.toList());

        return filteredReservations;
    }

    public void removeNotActiveReservation(Integer id) {
        Reservation reservation = reservationDao.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        if(!reservation.getActive()) {
            reservation.getItem().toggleState();
            reservationDao.delete(reservation);
        }
    }


    public List<Item> filterItems(String genre, String author, String title) {
        if(genre != null && genre.isEmpty()) {
            genre = null;
        }

        if(author != null && author.isEmpty()) {
            author = null;
        }

        if(title != null && title.isEmpty()) {
            title = null;
        }

        if(genre == null && author == null && title == null){

            return itemDao.findByType(Item.Type.book);
        }
        if (genre == null) {

            if(author == null) {
                return itemDao.findByName(title);
            }
            if (title == null) {
                return itemDao.findByAuthor(author);
            }

            return itemDao.findByNameAndAuthor(title, author);

        } else if (author == null) {

            if (title == null) {
                return itemDao.findByGenre(genre);
            }else {
                return itemDao.findByNameAndGenre(title, genre);
            }

        }else if (title == null) {

            return itemDao.findByAuthorAndGenre(author, genre);

        }else{

            return itemDao.findByNameAndAuthorAndGenre(title, author,genre);

        }
    }
}
