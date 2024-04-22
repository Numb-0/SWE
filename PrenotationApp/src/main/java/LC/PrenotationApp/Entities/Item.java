package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "Items")
public class Item {
    public Item() {
    }
    public Item(Item.Type type) {
        // Are attributes already NULL?
        this.type = type;
        this.state = false; // Not reserved
    }

    public enum Type {
        book,
        seats,
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Type type;

    private String author;

    private String genre;

    private String complex;

    private boolean state;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }
}