package LC.PrenotationApp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UtentiForm {
    @NotNull
    @Size(min=4, max=30)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username ) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email ) {
        this.email = email;
    }
}