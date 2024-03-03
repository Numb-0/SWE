package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name= "Prenotazioni")
public class Prenotazione {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer dataInizio;
    private Integer dataFine;
    private Boolean concluso;

    @ManyToOne
    @JoinColumn(name = "Id_Prenotabile")
    private Prenotabile prenotabile;

    @ManyToOne
    @JoinColumn(name = "Id_Utente")
    private Utente utente;

    protected Prenotazione() {}
}