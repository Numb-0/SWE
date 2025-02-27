package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Item;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ItemDao extends CrudRepository<Item, Integer> {

    Item findById(long id);

    List<Item> findByNameAndAuthorAndGenre(String name, String author, String genre);

    List<Item> findByNameAndAuthor(String name, String author);

    List<Item> findByNameAndGenre(String name, String genre);

    List<Item> findByAuthorAndGenre(String author, String genre);

    List<Item> findByName(String name);

    List<Item> findByAuthor(String author);

    List<Item> findByGenre(String genre);

    List<Item> findByType(Item.Type type);

    List<Item> findByState(boolean state);
}