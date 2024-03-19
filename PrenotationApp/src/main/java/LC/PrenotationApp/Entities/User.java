package LC.PrenotationApp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name="Users")
public class
User {
    public enum Role {
        user,
        staff,
        manager
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Role role;
    @NotNull
    private String username;
    @NotNull
    @Size(min = 3, max = 30)
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.user;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) { this.role = role; }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public Role getRole() {return this.role;}

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Prenotation> prenotations;
}