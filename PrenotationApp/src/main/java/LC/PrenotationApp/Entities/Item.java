package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "Items")
public class Item {

    public enum Type {
        libro,
        posto
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Type type;

    private String author;

    private String genre;

    private String complex;

    private boolean state ;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    protected Item() {}
}