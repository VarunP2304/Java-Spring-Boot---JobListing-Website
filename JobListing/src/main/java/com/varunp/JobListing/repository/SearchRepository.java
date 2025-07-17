package com.varunp.JobListing.repository;

import com.varunp.JobListing.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String Text);
}
