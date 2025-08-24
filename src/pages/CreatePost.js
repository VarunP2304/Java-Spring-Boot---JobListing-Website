import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../App';

const CreatePost = () => {
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        title: '',
        company: '',
        location: '',
        description: '',
        experience: 0,
        techStack: ''
    });

    if (!user) {
        return <div className="form-container">
            <h2>Access Denied</h2>
            <p>Please sign in to post a new job.</p>
        </div>
    }

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const postData = {
            ...formData,
            experience: parseInt(formData.experience, 10),
            techStack: formData.techStack.split(',').map(tech => tech.trim()),
        };

        try {
            await axios.post('http://localhost:8080/api/job', postData);
            navigate('/');
        } catch (error) {
            console.error('Failed to create post:', error);
        }
    };

    return (
        <div className="form-container">
            <h2>Create a New Job Post</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Job Title</label>
                    <input type="text" name="title" onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Company Name</label>
                    <input type="text" name="company" onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Location</label>
                    <input type="text" name="location" onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Job Description</label>
                    <textarea name="description" rows="5" onChange={handleChange} required></textarea>
                </div>
                <div className="form-group">
                    <label>Experience (years)</label>
                    <input type="number" name="experience" onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Technology Stack (comma-separated)</label>
                    <input type="text" name="techStack" placeholder="e.g., Java, Spring, React" onChange={handleChange} required />
                </div>
                <button type="submit" className="btn">Submit Post</button>
            </form>
        </div>
    );
};

export default CreatePost;