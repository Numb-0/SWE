package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {
    @Autowired
    private ItemDao itemDao;

}