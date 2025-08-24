import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { AuthContext } from '../App';

const EditPost = () => {
    const { id } = useParams();
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

    useEffect(() => {
        const fetchPost = async () => {
            try {
                const { data } = await axios.get(`http://localhost:8080/api/job/${id}`);
                setFormData({
                    ...data,
                    techStack: (data.techStack || []).join(', ') // Convert array back to comma-separated string for input
                });
            } catch (error) {
                console.error('Failed to fetch post for editing:', error);
            }
        };
        fetchPost();
    }, [id]);

    if (!user) {
        return <div className="form-container">
            <h2>Access Denied</h2>
            <p>Please sign in to edit a job post.</p>
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
            await axios.put(`http://localhost:8080/api/job/${id}`, postData);
            navigate(`/post/${id}`); // Go back to the detail page after editing
        } catch (error) {
            console.error('Failed to update post:', error);
        }
    };

    return (
        <div className="form-container">
            <h2>Edit Job Post</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Job Title</label>
                    <input type="text" name="title" value={formData.title} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Company Name</label>
                    <input type="text" name="company" value={formData.company} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Location</label>
                    <input type="text" name="location" value={formData.location} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Job Description</label>
                    <textarea name="description" rows="5" value={formData.description} onChange={handleChange} required></textarea>
                </div>
                <div className="form-group">
                    <label>Experience (years)</label>
                    <input type="number" name="experience" value={formData.experience} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Technology Stack (comma-separated)</label>
                    <input type="text" name="techStack" value={formData.techStack} placeholder="e.g., Java, Spring, React" onChange={handleChange} required />
                </div>
                <button type="submit" className="btn">Update Post</button>
            </form>
        </div>
    );
};

export default EditPost;