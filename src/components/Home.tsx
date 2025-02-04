import React,{useEffect} from "react";
import { useNavigate } from "react-router-dom"; 
import "../styles/App.css";

const Home: React.FC = () => {
    const navigate = useNavigate();

    const validateToken = (token: string | null): boolean => {
      if (!token) return false;
      try {
          const { exp } = JSON.parse(atob(token.split(".")[1])); 
          return Date.now() <= exp * 1000; 
      } catch {
          return false; 
      }
  };
  
  useEffect(() => {
    const token = localStorage.getItem("token");
    
    if (!validateToken(token)) {
        
        localStorage.removeItem("token");
        navigate("/login");
    }
}, [navigate]); 

    const handleLogout = () => {
      localStorage.removeItem("token"); 
      navigate("/login"); 
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
