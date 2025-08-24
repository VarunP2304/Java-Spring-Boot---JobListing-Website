package com.varunp.JobListing.controller;

import com.varunp.JobListing.model.JobPost;
import com.varunp.JobListing.repository.JobPostRepository;
import com.varunp.JobListing.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobPostController {

    @Autowired private JobPostRepository repo;
    @Autowired private SearchRepository srepo;

    // --- NEW: Add this search method back ---
    @GetMapping("/jobs/search/{text}")
    public List<JobPost> searchJobs(@PathVariable String text) {
        return srepo.findByText(text);
    }


    // Endpoint for CANDIDATES to see all jobs, sorted by date
    @GetMapping("/jobs")
    public List<JobPost> getAllJobs() {
        return repo.findAllByOrderByCreatedAtDesc();
    }

    // Endpoint for EMPLOYERS to see only their jobs, sorted by date
    @GetMapping("/my-jobs")
    public List<JobPost> getEmployerJobs(@AuthenticationPrincipal OAuth2User principal) {
        String employerEmail = principal.getAttribute("email");
        return repo.findByEmployerEmailOrderByCreatedAtDesc(employerEmail);
    }

    // GET a single job post (for details page)
    @GetMapping("/job/{id}")
    public ResponseEntity<JobPost> getJobById(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST (Create) a new job post
    @PostMapping("/job")
    public JobPost createJob(@RequestBody JobPost jobPost, @AuthenticationPrincipal OAuth2User principal) {
        jobPost.setEmployerEmail(principal.getAttribute("email"));
        jobPost.setEmployerName(principal.getAttribute("name"));
        jobPost.setCreatedAt(Instant.now());
        return repo.save(jobPost);
    }

    // PUT (Update) an existing job post
    @PutMapping("/job/{id}")
    public ResponseEntity<?> updateJob(@PathVariable String id, @RequestBody JobPost updatedPost, @AuthenticationPrincipal OAuth2User principal) {
        String currentUserEmail = principal.getAttribute("email");
        return repo.findById(id)
                .map(post -> {
                    if (!post.getEmployerEmail().equals(currentUserEmail)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    post.setTitle(updatedPost.getTitle());
                    post.setCompany(updatedPost.getCompany());
                    post.setLocation(updatedPost.getLocation());
                    post.setDescription(updatedPost.getDescription());
                    post.setExperience(updatedPost.getExperience());
                    post.setTechStack(updatedPost.getTechStack());
                    return ResponseEntity.ok(repo.save(post));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a job post by its ID
    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id, @AuthenticationPrincipal OAuth2User principal) {
        String currentUserEmail = principal.getAttribute("email");
        return repo.findById(id)
                .map(post -> {
                    if (!post.getEmployerEmail().equals(currentUserEmail)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).<Void>build();
                    }
                    repo.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}