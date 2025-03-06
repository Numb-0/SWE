package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    //User findByPassword(String password);
}
