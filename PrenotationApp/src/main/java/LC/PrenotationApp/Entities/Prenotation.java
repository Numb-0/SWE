package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Prenotations")
public class Prenotation {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer startDate;
    private Integer endDate;
    private Boolean returned;

    @ManyToOne
    @JoinColumn(name = "Id_Item")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Id_User")
    private User user;

    protected Prenotation() {}
}