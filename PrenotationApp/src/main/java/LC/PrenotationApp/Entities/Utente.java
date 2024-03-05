package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "utenti")
public class Utente {
    public enum Role {
        user,
        staff,
        manager
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String email;
    private Role role;

    protected Utente() {}

    public Utente(String userName, String password, String email) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.role = Role.user;
    }
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Recensione> recensioni;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;
}