package com.varunp.JobListing.repository;

import com.varunp.JobListing.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // This method allows Spring to find a user by their email address
    Optional<User> findByEmail(String email);
}