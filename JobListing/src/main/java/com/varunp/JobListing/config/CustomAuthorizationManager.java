package com.varunp.JobListing.config;

import com.varunp.JobListing.model.User;
import com.varunp.JobListing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision; // Import this class
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager {

    @Autowired
    private UserRepository userRepository;

    // Change return type to AuthorizationDecision
    public AuthorizationDecision isEmployer(Supplier<Authentication> authenticationSupplier) {
        return hasRole(authenticationSupplier, "EMPLOYER");
    }

    // Change return type to AuthorizationDecision
    public AuthorizationDecision isCandidate(Supplier<Authentication> authenticationSupplier) {
        return hasRole(authenticationSupplier, "CANDIDATE");
    }

    // Change return type to AuthorizationDecision
    private AuthorizationDecision hasRole(Supplier<Authentication> authenticationSupplier, String expectedRole) {
        Authentication authentication = authenticationSupplier.get();
        if (!(authentication.getPrincipal() instanceof OAuth2User)) {
            return new AuthorizationDecision(false);
        }

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = principal.getAttribute("email");

        boolean isAuthorized = userRepository.findByEmail(email)
                .map(User::getRole)
                .map(role -> role != null && role.equals(expectedRole))
                .orElse(false);

        // Wrap the boolean result in an AuthorizationDecision object
        return new AuthorizationDecision(isAuthorized);
    }
}