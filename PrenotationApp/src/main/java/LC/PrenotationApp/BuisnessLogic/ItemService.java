package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.Entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemDao itemDao;

    public void saveItem(Item item) {
        // no control for now
        itemDao.save(item);
    }
}