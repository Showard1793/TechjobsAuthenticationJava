package org.launchcode.techjobsauth.controllers;

import org.launchcode.techjobsauth.models.User;
import org.launchcode.techjobsauth.models.dto.LoginFormDTO;
import org.launchcode.techjobsauth.models.dto.RegisterFormDTO;
import org.launchcode.techjobsauth.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // GET: Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerFormDTO", new RegisterFormDTO());
        return "register";  // Return the register form template (register.html)
    }

    // POST: Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterFormDTO registerFormDTO, Model model) {
        // Check if the passwords match
        if (!registerFormDTO.getPassword().equals(registerFormDTO.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Username already taken");
            return "register";
        }

        // Create and save the new user
        User newUser = new User(registerFormDTO.getUsername(), passwordEncoder.encode(registerFormDTO.getPassword()));
        userRepository.save(newUser);

        // Authenticate and create a new session
        authenticateUser(registerFormDTO.getUsername(), registerFormDTO.getPassword());

        return "redirect:/home";  // Redirect to home page after successful registration
    }

    // GET: Show login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";  // Return the login form template (login.html)
    }

    // POST: Handle login form submission
    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginFormDTO loginFormDTO, Model model) {
        Optional<User> userOptional = userRepository.findByUsername(loginFormDTO.getUsername());
        if (!userOptional.isPresent()) {
            model.addAttribute("error", "User not found");
            return "login";
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginFormDTO.getPassword(), user.getPassword())) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        // Authenticate and create a new session
        authenticateUser(loginFormDTO.getUsername(), loginFormDTO.getPassword());

        return "redirect:/home";  // Redirect to home page after successful login
    }

    // Handle logout
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();  // Clear the security context (session)
        return "redirect:/auth/login";  // Redirect to the login page after logout
    }

    // Utility method to authenticate the user and store the session
    private void authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
