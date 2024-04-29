package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Item;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ItemDao extends CrudRepository<Item, Integer> {

    Item findById(long id);
    @Query("SELECT i FROM Item i WHERE i.type = :type")
    List<Item> findItemsByType(@Param("type") Item.Type type);
}