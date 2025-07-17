package com.varunp.JobListing.controller;

import com.varunp.JobListing.model.Post;
import com.varunp.JobListing.repository.PostRepository;
import com.varunp.JobListing.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostRepository repo;

    @Autowired
    SearchRepository srepo;

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text) {
        return srepo.findByText(text);
    }

    // Add this POST endpoint
    @PostMapping("/post")
    @CrossOrigin
    public Post createPost(@RequestBody Post post) {
        return repo.save(post);
    }
}