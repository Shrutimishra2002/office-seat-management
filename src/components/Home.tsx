import React from "react";
import { useNavigate } from "react-router-dom"; 
import "../styles/App.css";// <-- Change here

const Home: React.FC = () => {
    const navigate = useNavigate();
  
    const handleLogout = () => {
      localStorage.removeItem("token"); // Remove token from localStorage
      navigate("/login"); // Redirect to login page
    };
  
    return (
      <div>
        <h2>Hello Shruti</h2>
        <p>Intern in Olyv</p>
        <button onClick={handleLogout}>Logout</button>
      </div>
    );
  };
  
  export default Home;
