package LC.PrenotationApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import LC.PrenotationApp.DAO.ItemDao;
import LC.PrenotationApp.DAO.ReservationDao;
import LC.PrenotationApp.DAO.UserDao;
import LC.PrenotationApp.Entities.CustomUserDetails;
import LC.PrenotationApp.Entities.Item;
import LC.PrenotationApp.Entities.Reservation;
import LC.PrenotationApp.Entities.User;
import LC.PrenotationApp.Controller.StaffController;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;




@SpringBootTest
@Transactional
class StaffTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private StaffController staffController;

    private User mockUser;
    private Item mockBook;
    private Reservation mockReservation;
    private Model mockModel;

    @BeforeEach
    public void setUp() {
        mockUser = new User("mockuser", "mockuser");
        userDao.save(mockUser);

        mockBook = new Item(Item.Type.book);
        mockBook.setName("mockbook");
        mockBook.setGenre("mockgenre");
        mockBook.setAuthor("mockauthor");
        itemDao.save(mockBook);

        mockReservation = new Reservation(mockUser, mockBook);
        reservationDao.save(mockReservation);
        mockModel = new ExtendedModelMap();
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
    public void testGetUserReservations() {
        // Set up authenticated user
        CustomUserDetails mockUserDetails = new CustomUserDetails(mockUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockModel.addAttribute("user_reservations", new ArrayList<Reservation>());
        staffController.getUserReservations(mockModel, mockUser.getUsername());
        var foundReservations = mockModel.getAttribute("user_reservations");

        assertEquals(mockReservation, ((List<Reservation>)foundReservations).getFirst());
    }

    @Test
    public void testActivationReservation() throws NotFoundException {
        var reservationId = mockReservation.getId().longValue();
        staffController.activateReservation(reservationId);
        assertEquals(true, mockReservation.getActive());
    }

    @Test
    public void testCloseReservation() throws NotFoundException {
        // Close can happen if the reservation has been activated
        var reservationId = mockReservation.getId().longValue();

        staffController.activateReservation(reservationId);
        assertEquals(true, mockReservation.getActive());
        assertEquals(true, mockBook.getState());

        // We manually set expired here so that the reservation can be closed
        mockReservation.setExpired(true);
        // Reservation deactivated and book is free
        staffController.closeReservation(reservationId);
        assertEquals(false, mockReservation.getActive());
        assertEquals(false, mockBook.getState());

    }

 
}
