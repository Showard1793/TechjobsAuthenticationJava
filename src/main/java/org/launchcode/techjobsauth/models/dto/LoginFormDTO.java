package org.launchcode.techjobsauth.models.dto;

public class LoginFormDTO {

    private String username;
    private String password;

    // Default constructor
    public LoginFormDTO() {}

    // Constructor to initialize with fields
    public LoginFormDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
