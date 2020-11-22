package net.javaguides.springboot.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

    @Size(min = 3, max = 20)
    private String username;

    @Size(max = 50)

    private String email;

    private String role;

    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
