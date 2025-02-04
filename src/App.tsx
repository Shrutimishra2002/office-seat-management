import React from "react";
import { useNavigate } from "react-router-dom";
import "./styles/App.css";

const App = () => {
  const navigate = useNavigate();

  return (
    <div className="app-container">
      <div className="content-box">
        <h1>Office Seat Management</h1>
        <h3>By Shruti Mishra</h3>
        <div className="button-container">
          <button 
          className="login-button" 
          onClick={() => navigate("/login")}>
            Login 
          </button>
          <br />
          <br />
          <button className="signup-button" 
          onClick={() => navigate("/signup")}>
            Signup
          </button>
        </div>
      </div>
    </div>
  );
};

export default App;
