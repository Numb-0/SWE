package LC.PrenotationApp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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


    protected Prenotabile() {}
}