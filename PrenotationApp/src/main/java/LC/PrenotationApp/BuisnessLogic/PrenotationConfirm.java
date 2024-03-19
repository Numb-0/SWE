package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.DAO.PrenotationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotationConfirm {
    @Autowired
    private PrenotationDao prenotationDao;
    @Autowired
    private ItemDao itemDao;

}