/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Header.css"; 

const Header: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const navigate = useNavigate();

  useEffect(() => {
    const storedUsername = localStorage.getItem("username");
    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);
  const handleLogout = () => {
    try {
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      localStorage.removeItem("username");
      localStorage.removeItem("userId");
      localStorage.removeItem("reservedSeat");
      localStorage.removeItem("confirmationMessage");
      localStorage.removeItem("assignedSeatId");
    
      navigate("/login");
    } catch (error) {
      console.error("Logout failed:", error);
    }
  };

  return (
    <div className="header">
      <span className="username">Welcome, {username}!</span>
      <button onClick={handleLogout} className="logout-button">
        Logout
      </button>
    </div>
  );
};

export default Header;
