import React, { useState, useEffect, useContext } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../App';

const PostDetail = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const { user } = useContext(AuthContext);
    const [post, setPost] = useState(null);

    useEffect(() => {
        const fetchPost = async () => {
            if (!user) return; // Don't fetch if user isn't loaded yet
            try {
                const { data } = await axios.get(`http://localhost:8080/api/job/${id}`);
                setPost(data);
            } catch (error) {
                console.error('Failed to fetch post:', error);
                navigate('/'); // Redirect home if post not found
            }
        };
        fetchPost();
    }, [id, navigate, user]);

    const handleDelete = async () => {
        if (window.confirm('Are you sure you want to delete this post?')) {
            try {
                await axios.delete(`http://localhost:8080/api/job/${id}`);
                navigate('/'); // Redirect to homepage on successful deletion
            } catch (error) {
                console.error('Failed to delete post:', error);
                alert('You do not have permission to delete this post.');
            }
        }
    };
    
    const handleApply = () => {
        alert(`Thank you for your interest in the ${post.title} position! Your application has been submitted.`);
    };

    const renderActionButtons = () => {
        if (!user || !post) return null;

        if (user.role === 'CANDIDATE') {
            return <button onClick={handleApply} className="btn">Apply Now</button>;
        }

        if (user.role === 'EMPLOYER' && user.email === post.employerEmail) {
            return (
                <div style={{ display: 'flex', gap: '1rem' }}>
                    <Link to={`/edit/${id}`} className="btn">Edit</Link>
                    <button onClick={handleDelete} className="btn" style={{ background: '#dc3545', borderColor: '#dc3545' }}>Delete</button>
                </div>
            );
        }
        
        return null;
    };

    if (!post) return <div>Loading...</div>;

    return (
        <div className="form-container">
            <h2>{post.title}</h2>
            <p><strong>Company:</strong> {post.company}</p>
            <p><strong>Location:</strong> {post.location}</p>
            <p><strong>Posted by:</strong> {post.employerName || 'N/A'}</p>
            <p><strong>Experience Required:</strong> {post.experience} years</p>
            <p><strong>Description:</strong> {post.description}</p>
            <div>
                <strong>Skills Required:</strong>
                <div className="tech-stack" style={{ marginTop: '0.5rem' }}>
                    {(post.techStack || []).map((tech, index) => (
                        <span key={index} className="tech-tag">{tech}</span>
                    ))}
                </div>
            </div>
            <div style={{ marginTop: '2rem' }}>
                {renderActionButtons()}
            </div>
        </div>
    );
};

export default PostDetail;