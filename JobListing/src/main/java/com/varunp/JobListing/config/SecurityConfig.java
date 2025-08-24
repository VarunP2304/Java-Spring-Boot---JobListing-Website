package com.varunp.JobListing.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String allowedMethods;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private CustomAuthorizationManager customAuthorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Publicly accessible endpoint to check user auth status
                        .requestMatchers("/api/user").permitAll()
                        // Endpoint for logged-in users to set their role
                        .requestMatchers("/api/user/role").authenticated()

                        // Employer-only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/job")
                            .access((authentication, context) -> customAuthorizationManager.isEmployer(authentication))
                        .requestMatchers(HttpMethod.PUT, "/api/job/**")
                            .access((authentication, context) -> customAuthorizationManager.isEmployer(authentication))
                        .requestMatchers(HttpMethod.DELETE, "/api/job/**")
                            .access((authentication, context) -> customAuthorizationManager.isEmployer(authentication))
                        .requestMatchers(HttpMethod.GET, "/api/my-jobs")
                            .access((authentication, context) -> customAuthorizationManager.isEmployer(authentication))
                        
                        // Candidate-only endpoints (includes search)
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**")
                            .access((authentication, context) -> customAuthorizationManager.isCandidate(authentication))
                        
                        // Endpoint to view a single job, accessible to both roles
                        .requestMatchers(HttpMethod.GET, "/api/job/**")
                            .access((authentication, context) -> {
                                boolean isAuthorized = customAuthorizationManager.isEmployer(authentication).isGranted() ||
                                                       customAuthorizationManager.isCandidate(authentication).isGranted();
                                return new AuthorizationDecision(isAuthorized);
                            })
                        
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl(frontendUrl, true)
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessUrl(frontendUrl)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedOrigins.split(",")));
        configuration.setAllowedMethods(List.of(allowedMethods.split(",")));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}