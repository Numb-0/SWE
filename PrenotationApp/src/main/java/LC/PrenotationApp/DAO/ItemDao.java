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
    List<Item> findItemsByType(@Param("type") Item.Type type);
}