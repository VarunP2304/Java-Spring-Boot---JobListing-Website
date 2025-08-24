package com.varunp.JobListing.repository;

import com.varunp.JobListing.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobPostRepository extends MongoRepository<JobPost, String> {
    // UPDATED: Find jobs by owner's email, sorted by creation date
    List<JobPost> findByEmployerEmailOrderByCreatedAtDesc(String employerEmail);

    // NEW: Find all jobs, sorted by creation date
    List<JobPost> findAllByOrderByCreatedAtDesc();
}