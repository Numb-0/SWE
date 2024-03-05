package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.PrenotabiliDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorePrenotabili {
    @Autowired
    private PrenotabiliDao prenotabiliDao;

}