
import React, { useState } from "react";
import axios, { AxiosError } from "axios";
import { Link, useNavigate } from "react-router-dom"; 
import "../styles/LoginStyles.css";

const Login = () => {
    const [loginData, setLoginData] = useState({
        email: "",
        password: "",
    });

    const [message, setMessage] = useState<string>("");
    const navigate = useNavigate(); 

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value,
        });

    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage("");

        try {
            const response = await axios.post("http://localhost:9000/users/login", loginData);
            if (response.status === 200) {
                const {token, role, username }=response.data;
    
                localStorage.setItem("token", token);
                localStorage.setItem("role", role);
                localStorage.setItem("username", username);
                localStorage.setItem("userId",response.data.userId);
                console.log("Login successful, user ID stored:", response.data.userId);
                setMessage("Login successful!");

                if (role === "admin") {
                    navigate("/admin-dashboard");  
                } else {
                    navigate("/user-dashboard");   
                }
            }
        } catch (error: unknown) {
            console.log(error);
            const axiosError = error as AxiosError;
            setMessage(axiosError.response?.data as string || "Login failed.");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={loginData.email}
                    onChange={handleChange}
                    required
                />
                <br />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={loginData.password}
                    onChange={handleChange}
                    required
                />
                <br />
                <button type="submit">Log In</button>
            </form>
            {message && <p>{message}</p>}

            <div className="signup-container">
                <p>
                    Create a new account? <Link to="/signup">Sign Up</Link>
                </p>
            </div>
        </div>
    );
};

export default Login;
