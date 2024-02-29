package LC.PrenotationApp;

import org.springframework.data.repository.CrudRepository;

import LC.PrenotationApp.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PrenotazioniDao extends CrudRepository<Prenotazione, Integer> {

}