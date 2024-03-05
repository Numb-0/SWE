package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "Prenotabili")
public class Prenotabile {

    public enum Type {
        libro,
        posto
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Type type;

    private String autore;

    private String genere;

    private String plesso;

    private boolean stato ;

    @OneToMany(mappedBy = "prenotabile", cascade = CascadeType.ALL)
    private Set<Recensione> recensioni;

    @OneToMany(mappedBy = "prenotabile", cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;

    protected Prenotabile() {}
}