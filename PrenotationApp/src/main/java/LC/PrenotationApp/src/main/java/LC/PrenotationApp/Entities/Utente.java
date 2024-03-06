package LC.PrenotationApp.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="Utenti")
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

    public Utente() {}

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.user;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Recensione> recensioni;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;
}