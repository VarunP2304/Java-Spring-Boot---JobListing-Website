import React, { useState, useEffect, createContext } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import axios from "axios";

// Import all your pages
import LandingPage from "./pages/LandingPage";
import ChooseRole from "./pages/ChooseRole";
import CandidateFeed from "./pages/CandidateFeed";
import EmployerDashboard from "./pages/EmployerDashboard"; // Ensure this import is present
import PostDetail from "./pages/PostDetail";
import EditPost from "./pages/EditPost";
import CreatePost from "./pages/CreatePost";
import Header from "./components/Header";

export const AuthContext = createContext();
axios.defaults.withCredentials = true;

const AppRoutes = () => {
    const { user } = React.useContext(AuthContext);

    if (!user) {
        // Logged-out users only see the landing page
        return <Routes><Route path="*" element={<LandingPage />} /></Routes>;
    }

    if (!user.role) {
        // Logged-in users without a role must choose one
        return <Routes><Route path="*" element={<ChooseRole />} /></Routes>;
    }

    if (user.role === 'EMPLOYER') {
        // Routes for Employers
        return (
            <Routes>
                <Route path="/create" element={<CreatePost />} />
                <Route path="/post/:id" element={<PostDetail />} />
                <Route path="/edit/:id" element={<EditPost />} />
                <Route path="*" element={<EmployerDashboard />} />
            </Routes>
        );
    }

    if (user.role === 'CANDIDATE') {
        // Routes for Candidates
        return (
            <Routes>
                <Route path="/post/:id" element={<PostDetail />} />
                <Route path="*" element={<CandidateFeed />} />
            </Routes>
        );
    }

    return null; // Should not be reached
};

function App() {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const { data } = await axios.get("http://localhost:8080/api/user");
                if (data.authenticated) {
                    setUser(data);
                }
            } catch (error) {
                console.error("Error fetching user:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchUser();
    }, []);

    if (loading) return <div>Loading...</div>;

    return (
        <AuthContext.Provider value={{ user, setUser }}>
            <BrowserRouter>
                <Header />
                <main className="container">
                    <AppRoutes />
                </main>
            </BrowserRouter>
        </AuthContext.Provider>
    );
}

export default App;