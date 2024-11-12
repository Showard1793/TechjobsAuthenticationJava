package org.launchcode.techjobsauth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Static BCryptPasswordEncoder to encode the password
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Default constructor (no args)
    public User() {}

    // Constructor with arguments
    public User(String username, String password) {
        this.username = username;
        // Encode the password using BCryptPasswordEncoder
        this.password = passwordEncoder.encode(password);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.password = passwordEncoder.encode(password);  // Always encode when setting the password
    }

    // Method to check if a raw password matches the encoded one
    public boolean checkPassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
}
