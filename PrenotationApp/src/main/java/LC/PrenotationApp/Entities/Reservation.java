package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;

@Entity
@Table(name= "Reservations")
public class Reservation {

    public Reservation() {}

    public Reservation(User user, Item item) {
        this.user = user;
        this.item = item;
        this.endDate = LocalDate.now().plusDays(5);
        this.expired = false;
        if (!this.item.getState())
            this.item.toggleState();
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean expired;

    @ManyToOne
    @JoinColumn(name = "Id_Item")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Id_User")
    private User user;

    public void ReservationStart(LocalDate date) {
        this.startDate = date;
        this.endDate = this.endDate.plusMonths(1);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}