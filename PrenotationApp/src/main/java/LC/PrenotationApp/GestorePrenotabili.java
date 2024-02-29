package LC.PrenotationApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorePrenotabili {
    @Autowired // This means to get the bean called userRepository
    private PrenotabiliDao prenotabiliDao;

}