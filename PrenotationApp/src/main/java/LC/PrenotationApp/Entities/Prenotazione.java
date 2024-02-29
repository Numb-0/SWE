package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Prenotazioni")
public class Prenotazione {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long idPrenotabile;

    private Long idUtente;
    private Integer dataInizio;
    private Integer dataFine;

    private Boolean concluso;


    protected Prenotazione() {}
}