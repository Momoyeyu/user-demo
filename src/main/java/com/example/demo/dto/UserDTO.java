package com.example.demo.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String email;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setId(Long id) { this.id = id; }
    public void setUsername(String name) { this.username = name; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
