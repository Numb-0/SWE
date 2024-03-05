package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.PrenotabiliDao;
import LC.PrenotationApp.DAO.PrenotazioniDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorePrenotazioni {
    @Autowired
    private PrenotazioniDao prenotazioniDao;
    @Autowired
    private PrenotabiliDao prenotabiliDao;

}
