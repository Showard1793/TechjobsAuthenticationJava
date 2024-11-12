package org.launchcode.techjobsauth.models.dto;

public class RegisterFormDTO {

    private String username;
    private String password;
    private String confirmPassword;

    // Default constructor
    public RegisterFormDTO() {}

    // Constructor to initialize with fields
    public RegisterFormDTO(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
