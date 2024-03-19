package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Reviews")
public class Review {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Item")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Id_User")
    private User user;
    private String review;


    protected Review() {}
}