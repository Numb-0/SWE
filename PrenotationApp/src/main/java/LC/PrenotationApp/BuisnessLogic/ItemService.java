package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.Entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemDao itemDao;

    public void saveItem(Item item) {
        // no control for now
        itemDao.save(item);
    }

    public List<Item> getBookItems() {
        return itemDao.findItemsByType(Item.Type.book);
    }

    public Item getItemById(long id) {
        return itemDao.findById(id);
    }

    public void updateItemById(long id, Item updatedItem) {
        Item item = itemDao.findById(id);
        if (item == null) {
            throw new IllegalArgumentException("Invalid item Id:" + id);
        }
        item.setName(updatedItem.getName());
        item.setAuthor(updatedItem.getAuthor());
        item.setGenre(updatedItem.getGenre());
        itemDao.save(item);
    }
}