package LC.PrenotationApp;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface testQuery extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

    User findById(long id);
}