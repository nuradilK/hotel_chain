package net.javaguides.springboot.payload.response;

import java.util.List;

public class JwtResponse {
    private Long id;
    private String email;
    private String jwtToken;
    //private List<String> roles;

    public JwtResponse(Long id, String email, String jwtToken) {
        this.id = id;
        this.email = email;
        this.jwtToken = jwtToken;
        //this.roles = roles;
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

}