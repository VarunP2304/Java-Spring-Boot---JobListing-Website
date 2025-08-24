import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../App';

const Header = () => {
    const { user } = useContext(AuthContext);

    const logout = () => {
        // Using a form POST is a more robust way to handle logout
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'https://joblisting-backend-gvl5.onrender.com/api/logout';
        document.body.appendChild(form);
        form.submit();
    };

    return (
        <header className="header">
            <div className="container">
                <nav className="nav">
                    <Link to="/" className="brand">JobListing</Link>
                    <div className="nav-links">
                        {user && (
                            <>
                                {/* --- FIX: Only show button if user is an EMPLOYER --- */}
                                {user.role === 'EMPLOYER' && (
                                    <Link to="/create" className="btn btn-secondary">Post a Job</Link>
                                )}
                                <div className="user-info">
                                    <img src={user.picture} alt={user.name} />
                                    <span>{user.name}</span>
                                </div>
                                <button onClick={logout} className="btn">Logout</button>
                            </>
                        )}
                    </div>
                </nav>
            </div>
        </header>
    );
};

export default Header;