package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Recensioni")
public class Recensione {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long idPrenotabile;

    private Long idUtente;
    private String recensione;


    protected Recensione() {}
}