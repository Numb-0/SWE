package LC.PrenotationApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import LC.PrenotationApp.Controller.ManagerController;
import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.DAO.UserDao;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.User;


@SpringBootTest
@Transactional
public class ManagerTests {
    @Autowired
	private UserDao userDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ManagerController managerController;

	private User mockUser;
    private User mockManager;
    private Item mockBook;

	@BeforeEach
	public void setUp() {
		mockUser = new User("mockuser", "mockuser");
		userDao.save(mockUser);

        mockManager = new User("mockmanager", "mockmanager");
        mockManager.setRole(User.Role.manager);
        userDao.save(mockManager);

		mockBook = new Item(Item.Type.book);
		mockBook.setName("mockbook");
		mockBook.setGenre("mockgenre");
		mockBook.setAuthor("mockauthor");
		itemDao.save(mockBook);
	}

	@AfterEach
	public void unSet() {
		userDao.delete(mockUser);
        userDao.delete(mockManager);
		itemDao.delete(mockBook);
		SecurityContextHolder.getContext().setAuthentication(null);
	}

    @Test
    public void testAddBook() {
        Item mockAddBook = new Item(Item.Type.book);
        mockAddBook.setName("maaaan");
        mockAddBook.setAuthor("man");
        mockAddBook.setGenre("Adventure");

        // Check if item is in database
        managerController.addBook(mockAddBook);
        assertEquals(itemDao.findById(mockAddBook.getId()), mockAddBook);

        // Clean up
        itemDao.delete(mockAddBook);
    }

    /* @Test
    public void testEditBook() {
        Item mockAddBook = new Item(Item.Type.book);
        mockAddBook.setName("maaaan");
        mockAddBook.setAuthor("man");
        mockAddBook.setGenre("Adventure");
        
        itemDao.save(mockAddBook);

        Item mockEditBook = new Item(Item.Type.book);
        mockEditBook.setName("cenerentola");

        //managerController.editBook(mockAddBook.getId(), mockEditBook, null);

        assertEquals(itemDao.findById(mockAddBook.getId()).getName(), mockEditBook.getName());
    } */
}
