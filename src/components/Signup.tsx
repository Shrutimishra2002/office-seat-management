
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; 
import "../styles/App.css";

const Signup = () => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });

    const [message, setMessage] = useState("");
    const [errorDetails, setErrorDetails] = useState("");
    const navigate = useNavigate(); 

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(""); 
        setErrorDetails("");

        // Check if all fields are filled
        if (!formData.firstName || !formData.lastName || !formData.email || !formData.password) {
            setMessage("All fields are required.");
            return;
        }

        try {
            const response = await axios.post("http://localhost:9000/users/signup", formData);

            if (response.status === 200) {
                setMessage("Signup successful!");

                // After successful signup, automatically log in the user
                const loginResponse = await axios.post("http://localhost:9000/users/login", {
                    email: formData.email,
                    password: formData.password,
                });

                console.log("loginResponse: ", loginResponse)

                if (loginResponse.status === 200) {
                    localStorage.setItem("token", loginResponse.data.token);
                    localStorage.setItem("username", loginResponse.data.username); 
                    localStorage.setItem("userId",loginResponse.data.userId); 
                    navigate("/login"); 
                }
            } else {
                setMessage("An error occurred during signup.");
            }
        } catch (error: unknown) {
            if (axios.isAxiosError(error)) {
                if (error.response) {
                    setMessage("Error signing up.");
                    setErrorDetails(error.response.data || "Unknown error.");
                } else if (error.request) {
                    setMessage("No response from the server. Please try again later.");
                    setErrorDetails(error.message);
                } else {
                    setMessage("An unexpected error occurred.");
                    setErrorDetails(error.message);
                }
            } else {
                setMessage("An unexpected error occurred.");
                setErrorDetails("Unknown error type.");
            }
        }
    };

    return (
        <div>
            <h2>Signup</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                />
                <br />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                />
                <br />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
                <br />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <br />
                <button type="submit">Sign Up</button>
            </form>
            {message && <p>{message}</p>}
            {errorDetails && <p style={{ color: "red" }}>{errorDetails}</p>}
        </div>
    );
};

export default Signup;
