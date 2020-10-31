package net.javaguides.springboot.payload.response;

import java.util.List;

public class JwtResponse {
    private Long id;
    private String email;
    private String accessToken;

    public JwtResponse(Long id, String email, String accessToken) {
        this.id = id;
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getAccessToken(){ return accessToken; }
    public void setAccessToken(String accessToken){  this.accessToken = accessToken; }
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
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}