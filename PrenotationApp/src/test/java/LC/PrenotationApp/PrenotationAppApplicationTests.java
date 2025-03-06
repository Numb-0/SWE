package LC.PrenotationApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.DAO.UserDao;
import LC.PrenotationApp.Entities.CustomUserDetails;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import LC.PrenotationApp.Controller.UserController;;



@SpringBootTest
@Transactional
class PrenotationAppApplicationTests {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private UserController userController;

	private User mockUser;
    private Item mockBook;

	@BeforeEach
	public void setUp() {
		mockUser = new User("mockuser", "mockuser");
		userDao.save(mockUser);

		mockBook = new Item(Item.Type.book);
		mockBook.setName("mockbook");
		mockBook.setGenre("mockgenre");
		mockBook.setAuthor("mockauthor");
		itemDao.save(mockBook);
	}

	@AfterEach
	public void unSet() {
		userDao.delete(mockUser);
		itemDao.delete(mockBook);
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Test
	void contextLoads() {
		assertThat(userDao).isNotNull();
		assertThat(itemDao).isNotNull();
		assertThat(reservationDao).isNotNull();
	}
	
	@Test
	void testUserCreation() {
		// Get the current number of users in the database
		long initialUserCount = userDao.count();

		// Create new users
		int newUsersCount = 5;
		for (int i = 0; i < newUsersCount; i++) {
			// User table has non-nullable username and password (user.role is set by default as user)
			User user = new User("testuser" + i, "testuser" + i);
			userDao.save(user);
		}

		// Get the total number of users after creating new ones
		long totalUserCount = userDao.count();

		assertThat(totalUserCount).isEqualTo(initialUserCount + newUsersCount);
	}

	@Test 
	void testUserReservation() {
		Reservation mockReservation = new Reservation(mockUser, mockBook);
		reservationDao.save(mockReservation);
		assertThat(reservationDao.findByUser(mockUser).get(0)).isEqualTo(mockReservation);
	}

	@Test
    public void testAddReservation() {
		// Reservation created using frontend model
        Reservation mockReservation = new Reservation();
		mockReservation.setItem(mockBook);

		// Set up authenticated user
		CustomUserDetails mockUserDetails = new CustomUserDetails(mockUser);
    	Authentication authentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());
    	SecurityContextHolder.getContext().setAuthentication(authentication);
		
        ResponseEntity<String> response = userController.addReservation(mockReservation);

        assertEquals("Reservation added successfully", response.getBody());
		assertEquals(mockReservation.getUser(), mockUser);

		// After the addReservation method the book state is set as reserved
		assertEquals(mockReservation.getItem().getState(), true);

		// Clean up
		reservationDao.delete(mockReservation);
    }

	@Test
    public void testAddReservationReserved() {
		// State is set to true on creation
        Reservation mockReservation = new Reservation(mockUser, mockBook);
		// Reservation already in database
		reservationDao.save(mockReservation);

		// Mock reservation for mockuser2
		Reservation mockReservation2 = new Reservation();
		mockReservation2.setItem(mockBook);

		User mockUser2 = new User("mockuser2", "mockuser2");
		userDao.save(mockUser2);

		// Set up authenticated user
		CustomUserDetails mockUserDetails = new CustomUserDetails(mockUser2);
    	Authentication authentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());
    	SecurityContextHolder.getContext().setAuthentication(authentication);

		// Testing if reservation is being added in database if the book state is reserved
        ResponseEntity<String> response = userController.addReservation(mockReservation);

        assertEquals("Book is reserved", response.getBody());
		assertEquals(reservationDao.findByUser(mockUser2).size(), 0);

		// Clean up
		reservationDao.delete(mockReservation);
    }

	@Test
	public void testRemoveReservationNotActive() {
        Reservation mockReservation = new Reservation(mockUser, mockBook);
		// Add mockReservation to database
		reservationDao.save(mockReservation);

		userController.removeReservation(mockReservation.getId().intValue());

		// Check if mockReservation is still in database
		assertEquals(reservationDao.findById(mockReservation.getId()), null);
	}

	@Test
	public void testRemoveReservationActive() {
        Reservation mockReservation = new Reservation(mockUser, mockBook);

		// Set active 
		mockReservation.setActive(true);

		// Add mockReservation to database
		reservationDao.save(mockReservation);

		userController.removeReservation(mockReservation.getId().intValue());

		// Check if mockReservation is still in database
		assertEquals(reservationDao.findById(mockReservation.getId()), mockReservation);
	}

}
