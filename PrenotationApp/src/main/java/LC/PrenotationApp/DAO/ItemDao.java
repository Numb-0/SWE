package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Item;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ItemDao extends CrudRepository<Item, Integer> {

    /* Item findById(long id);

    @Query("SELECT i FROM Item i WHERE i.name = :name AND i.author = :author AND i.genre = :genre")
    List<Item> findItemsByNameAndAuthorAndGenre(@Param("name") String name, @Param("author") String author, @Param("genre") String genre);

    @Query("SELECT i FROM Item i WHERE i.name = :name AND i.author = :author")
    List<Item> findItemsByNameAndAuthor(@Param("name") String name, @Param("author") String author);

    @Query("SELECT i FROM Item i WHERE i.name = :name AND i.genre = :genre")
    List<Item> findItemsByNameAndGenre(@Param("name") String name, @Param("genre") String genre);

    @Query("SELECT i FROM Item i WHERE i.author = :author AND i.genre = :genre")
    List<Item> findItemsByAuthorAndGenre(@Param("author") String author, @Param("genre") String genre);

    @Query("SELECT i FROM Item i WHERE i.name = :name")
    List<Item> findItemsByName(@Param("name") String name);

    @Query("SELECT i FROM Item i WHERE i.author = :author")
    List<Item> findItemsByAuthor(@Param("author") String author);

    @Query("SELECT i FROM Item i WHERE i.genre = :genre")
    List<Item> findItemsByGenre(@Param("genre") String genre);

    @Query("SELECT i FROM Item i WHERE i.type = :type")
    List<Item> findItemsByType(@Param("type") Item.Type type); */
    Item findById(long id);

    List<Item> findByNameAndAuthorAndGenre(String name, String author, String genre);

    List<Item> findByNameAndAuthor(String name, String author);

    List<Item> findByNameAndGenre(String name, String genre);

    List<Item> findByAuthorAndGenre(String author, String genre);

    List<Item> findByName(String name);

    List<Item> findByAuthor(String author);

    List<Item> findByGenre(String genre);

    List<Item> findByType(Item.Type type);

    // New query to get items with state = 0
    List<Item> findByState(boolean state);
}