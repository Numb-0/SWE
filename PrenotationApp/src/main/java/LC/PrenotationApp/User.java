package LC.PrenotationApp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    private String city;

    protected User() {}

    public User(String userName, String city) {
        this.userName = userName;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, userName='%s', city='%s']",
                id, userName, city);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return city;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public void setCity(String city) {
        this.city = city;
    }
}