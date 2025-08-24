import React from 'react';

const LandingPage = () => {
    const handleLogin = () => {
        // Both buttons trigger the same Google login endpoint
        window.location.href = "http://localhost:8080/oauth2/authorization/google";
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '10rem' }}>
            <h1>Welcome to JobListing</h1>
            <p>The easiest way to find your next opportunity or your next hire.</p>
            <div style={{ marginTop: '2rem', display: 'flex', justifyContent: 'center', gap: '2rem' }}>
                <button onClick={handleLogin} className="btn" style={{ padding: '1rem 2rem', fontSize: '1.2rem' }}>
                    I'm a Candidate<br/>
                    <small>Find a Job</small>
                </button>
                <button onClick={handleLogin} className="btn" style={{ padding: '1rem 2rem', fontSize: '1.2rem' }}>
                    I'm an Employer<br/>
                    <small>Post a Job</small>
                </button>
            </div>
        </div>
    );
};

export default LandingPage;