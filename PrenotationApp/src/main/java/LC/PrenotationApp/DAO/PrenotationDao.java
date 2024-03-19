package LC.PrenotationApp.DAO;

import LC.PrenotationApp.Entities.Prenotation;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PrenotationDao extends CrudRepository<Prenotation, Integer> {

}