package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Recensioni")
public class Recensione {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Prenotabile")
    private Prenotabile prenotabile;

    @ManyToOne
    @JoinColumn(name = "Id_Utente")
    private User user;
    private String recensione;


    protected Recensione() {}
}