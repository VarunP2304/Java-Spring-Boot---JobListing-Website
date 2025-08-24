package com.varunp.JobListing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Arrays;
import java.time.Instant;

@Document(collection = "jobpost")
public class JobPost {

    @Id
    private String id;

    private String title;       // Renamed from 'profile'
    private String description; // Renamed from 'desc'
    private int experience;     // Changed to int for better sorting/filtering
    private String[] techStack; // Renamed from 'techs'
    private String company;
    private String location;
    private Instant createdAt;
    private String employerEmail;
    private String employerName;

    // Constructors, Getters, and Setters

    public JobPost() {
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String[] getTechStack() {
        return techStack;
    }

    public void setTechStack(String[] techStack) {
        this.techStack = techStack;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmployerEmail() { return employerEmail; }
    public void setEmployerEmail(String employerEmail) { this.employerEmail = employerEmail; }
    public String getEmployerName() { return employerName; }
    public void setEmployerName(String employerName) { this.employerName = employerName; }

    @Override
    public String toString() {
        return "JobPost{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", experience=" + experience +
                ", techStack=" + Arrays.toString(techStack) +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}