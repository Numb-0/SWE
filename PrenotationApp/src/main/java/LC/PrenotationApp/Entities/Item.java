package LC.PrenotationApp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    @NotEmpty(message = "name cant be empty")
    private String name;
    private String author;

    private String genre;

    private boolean state;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

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

    public Boolean getState() {
        return this.state;
    }

    public void toggleState() {
        this.state = !this.state;
    }

}