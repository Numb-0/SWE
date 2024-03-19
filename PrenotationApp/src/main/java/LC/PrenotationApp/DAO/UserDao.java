package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByPassword(String password);

}
