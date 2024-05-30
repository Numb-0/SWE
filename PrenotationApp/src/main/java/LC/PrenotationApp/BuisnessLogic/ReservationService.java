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
        return reservationDao.findReservationsByUser(user);
    }
    // TODO
public List<Item> filterItems(String genre, String author, String title) {
        if(genre==null && author==null && title==null){
            return null;
        }
        if (genre == null){

            if(author == null){
                return itemDao.findItemsByName(title);
            }
            if (title == null){
                return itemDao.findItemsByAuthor(author);
            }

            return itemDao.findItemsByNameAndAuthor(title, author);

        } else if (author == null) {

            if (title == null){
                return itemDao.findItemsByGenre(genre);
            }else{
                return itemDao.findItemsByNameAndGenre(title, genre);
            }

        }else if (title == null){

            return itemDao.findItemsByAuthorAndGenre(author, genre);

        }else{

            return itemDao.findItemsByNameAndAuthorAndGenre(title, author,genre);

        }
    }
}
