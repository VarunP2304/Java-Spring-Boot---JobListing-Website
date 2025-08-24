import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

// You can reuse the JobCard component from CandidateFeed or create a separate one
const JobCard = ({ post }) => (
    <div className="job-card">
        <h3>{post.title || "-"}</h3>
        <p className="company-info">{post.company || "N/A"} - {post.location || "N/A"}</p>
        <p className="details">Required Experience: {post.experience || 0} years</p>
    </div>
);

const EmployerDashboard = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchEmployerPosts = async () => {
            try {
                // Fetch from the new employer-specific endpoint
                const { data } = await axios.get("http://localhost:8080/api/my-jobs");
                setPosts(data);
            } catch (error) {
                console.error("Failed to fetch employer posts:", error);
            }
        };
        fetchEmployerPosts();
    }, []);

    return (
        <div>
            <h2>Your Job Postings</h2>
            <div className="job-grid">
                {posts.filter(post => post.title).map(post => (
                    <Link to={`/post/${post.id}`} key={post.id} style={{ textDecoration: 'none', color: 'inherit' }}>
                        <JobCard post={post} />
                    </Link>
                ))}
            </div>
            {posts.length === 0 && <p>You have not posted any jobs yet. <Link to="/create">Post one now!</Link></p>}
        </div>
    );
};

export default EmployerDashboard;