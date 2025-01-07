import { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Link, useNavigate } from "react-router-dom";
import Login from "./components/Login";
import Signup from "./components/Signup";
import Home from "./components/Home";

// Utility to check token validity
const isTokenValid = () => {
  const token = localStorage.getItem("token");
  if (!token) return false;

  // Decode the token and check the expiration
  const decodedToken = JSON.parse(atob(token.split(".")[1])); // Decode JWT token
  const expiry = decodedToken.exp * 1000; // Convert to milliseconds
  return expiry > Date.now(); // If the token is expired
};

const ProtectedRoute = ({ element }: { element: JSX.Element }) => {
  const navigate = useNavigate();
  const [showExpiredPopup, setShowExpiredPopup] = useState(false);

  useEffect(() => {
    if (!isTokenValid()) {
      setShowExpiredPopup(true);
      setTimeout(() => {
        navigate("/login"); // Redirect to login page after a delay
      }, 2000);
    }
  }, [navigate]);

  if (showExpiredPopup) {
    return <div className="expired-popup">Your session has expired. Redirecting...</div>;
  }

  return element;
};

const App = () => {
  return (
      <Router>
          <div>
              <h1>User Management System</h1>
              <nav>
                  <Link to="/signup">Signup</Link> | <Link to="/login">Login</Link>
              </nav>
              <Routes>
                  <Route path="/signup" element={<Signup />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/home" element={<ProtectedRoute element={<Home />} />} /> {/* Protected Route */}
              </Routes>
          </div>
      </Router>
  );
};

export default App;
