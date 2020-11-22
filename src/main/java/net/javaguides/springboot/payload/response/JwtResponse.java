package net.javaguides.springboot.payload.response;

import net.javaguides.springboot.model.Role;

import java.util.List;

public class JwtResponse {
    private Long id;
    private String email;
    private String jwtToken;
    private Role role;

    public JwtResponse(Long id, String email, String jwtToken, Role role) {
        this.id = id;
        this.email = email;
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public String getAccessToken(){ return jwtToken; }

    public void setAccessToken(String jwtToken){  this.jwtToken = jwtToken; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}