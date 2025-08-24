package com.varunp.JobListing.controller;

import com.varunp.JobListing.model.User;
import com.varunp.JobListing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap; // Import HashMap
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.ok(Map.of("authenticated", false));
        }

        String email = principal.getAttribute("email");
        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(principal.getAttribute("name"));
            newUser.setPicture(principal.getAttribute("picture"));
            // Role is intentionally left null until the user chooses one
            return userRepository.save(newUser);
        });

        // --- START OF FIX ---
        // We use a HashMap because Map.of() does not allow null values,
        // and a new user's role will be null.
        Map<String, Object> userData = new HashMap<>();
        userData.put("authenticated", true);
        userData.put("name", user.getName());
        userData.put("email", user.getEmail());
        userData.put("picture", user.getPicture());
        userData.put("role", user.getRole()); // This is now safe, even if role is null

        return ResponseEntity.ok(userData);
        // --- END OF FIX ---
    }

    // ... (your setUserRole method remains the same)
    @PostMapping("/api/user/role")
    public ResponseEntity<?> setUserRole(@AuthenticationPrincipal OAuth2User principal, @RequestBody Map<String, String> payload) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        String email = principal.getAttribute("email");
        String role = payload.get("role");

        if (!"EMPLOYER".equals(role) && !"CANDIDATE".equals(role)) {
            return ResponseEntity.badRequest().body("Invalid role");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}