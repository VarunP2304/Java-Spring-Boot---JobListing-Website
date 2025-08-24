import React from 'react';
import axios from 'axios';

const ChooseRole = () => {
    const handleRoleSelection = async (role) => {
        try {
            await axios.post('https://joblisting-backend-gvl5.onrender.com/api/user/role', { role });
            
            // --- FIX: Instead of reloading, re-authenticate to refresh the session ---
            // This will be a seamless, instant redirect that creates a new session with the correct role.
            window.location.href = "https://joblisting-backend-gvl5.onrender.com/oauth2/authorization/google";

        } catch (error) {
            console.error('Failed to set role:', error);
        }
    };

    return (
        <div className="form-container" style={{ textAlign: 'center' }}>
            <h2>Choose Your Role</h2>
            <p>Please select how you'd like to use JobListing.</p>
            <div style={{ marginTop: '2rem', display: 'flex', justifyContent: 'center', gap: '2rem' }}>
                <button onClick={() => handleRoleSelection('CANDIDATE')} className="btn">
                    I am a Candidate
                </button>
                <button onClick={() => handleRoleSelection('EMPLOYER')} className="btn">
                    I am an Employer
                </button>
            </div>
        </div>
    );
};

export default ChooseRole;