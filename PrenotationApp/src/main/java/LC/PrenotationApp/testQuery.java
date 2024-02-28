package LC.PrenotationApp;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface testQuery extends CrudRepository<User, Long> {

    List<User> findByUserName(String username);

    User findById(long id);
}