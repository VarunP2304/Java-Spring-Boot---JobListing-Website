package com.varunp.JobListing.repository;

import com.varunp.JobListing.model.JobPost;
import java.util.List;

public interface SearchRepository {
    List<JobPost> findByText(String text);
}