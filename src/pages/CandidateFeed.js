import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const JobCard = ({ post }) => {
    const handleApply = () => {
        alert(`Thank you for applying for the ${post.title} position!`);
        // In a real app, this would trigger a modal or redirect.
    };

    return (
        <div className="job-card">
            {/* --- RESTORED JOB DETAILS --- */}
            <h3>{post.title || "-"}</h3>
            <p className="company-info">{post.company || "N/A"} - {post.location || "N/A"}</p>
            <p className="description">{post.description}</p>
            <p className="details">Required Experience: {post.experience || 0} years</p>
            <div className="tech-stack">
                {(post.techStack || []).map((tech, index) => (
                    <span key={index} className="tech-tag">{tech}</span>
                ))}
            </div>
            {/* --- END OF RESTORED DETAILS --- */}

            <div style={{ marginTop: '1rem' }}>
                <button className="btn" onClick={handleApply}>Apply Now</button>
            </div>
        </div>
    );
};

const CandidateFeed = () => {
    const [posts, setPosts] = useState([]);
    const [search, setSearch] = useState("");

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const endpoint = search
                    ? `http://localhost:8080/api/jobs/search/${search}`
                    : "http://localhost:8080/api/jobs";
                const { data } = await axios.get(endpoint);
                setPosts(data);
            } catch (error) {
                console.error("Failed to fetch posts:", error);
            }
        };
        fetchPosts();
    }, [search]);

    return (
        <div>
            <div className="search-bar">
                <input
                    type="text"
                    placeholder="Search for job titles, companies, or skills..."
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>
            <div className="job-grid">
                {posts.filter(post => post.title).map(post => (
                    // In the new flow, candidates click "Apply" on the card, not the card itself.
                    <JobCard post={post} key={post.id} />
                ))}
            </div>
        </div>
    );
};

export default CandidateFeed;