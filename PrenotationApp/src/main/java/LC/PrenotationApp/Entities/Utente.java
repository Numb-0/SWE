package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "Utenti")
public class Utente {

    public enum Role {
        user,
        staff,
        manger
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Recensione> recensioni;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;

    protected Utente() {}
}