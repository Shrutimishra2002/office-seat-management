// import React, { useState } from "react";
// import axios, { AxiosError } from "axios";
// import "../styles/App.css";

// const Login = () => {
//     const [loginData, setLoginData] = useState({
//         email: "",
//         password: "",
//     });

//     const [message, setMessage] = useState<string>("");

//     const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//         setLoginData({
//             ...loginData,
//             [e.target.name]: e.target.value,
//         });
//     };

//     const handleSubmit = async (e: React.FormEvent) => {
//         e.preventDefault();
//         setMessage(""); // Reset message

//         try {
//             const response = await axios.post("http://localhost:9000/login", loginData);
//             if (response.status === 200) {
//                 setMessage("Login successful!");
//             }
//         } catch (error: unknown) {
//             const axiosError = error as AxiosError;
//             setMessage(axiosError.response?.data as string || "Login failed.");
//         }
//     };

//     return (
//         <div>
//             <h2>Login</h2>
//             <form onSubmit={handleSubmit}>
//                 <input
//                     type="email"
//                     name="email"
//                     placeholder="Email"
//                     value={loginData.email}
//                     onChange={handleChange}
//                     required
//                 />
//                 <br />
//                 <input
//                     type="password"
//                     name="password"
//                     placeholder="Password"
//                     value={loginData.password}
//                     onChange={handleChange}
//                     required
//                 />
//                 <br />
//                 <button type="submit">Log In</button>
//             </form>
//             {message && <p>{message}</p>}
//         </div>
//     );
// };

// export default Login;


import React, { useState } from "react";
import axios, { AxiosError } from "axios";
import { useNavigate } from "react-router-dom"; // <-- Change here
import "../styles/App.css";

const Login = () => {
    const [loginData, setLoginData] = useState({
        email: "",
        password: "",
    });

    const [message, setMessage] = useState<string>("");
    const navigate = useNavigate(); // <-- Change here

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(""); // Reset message

        try {
            const response = await axios.post("http://localhost:9000/login", loginData);
            if (response.status === 200) {
                localStorage.setItem("token", response.data.token); // Store the token in localStorage
                setMessage("Login successful!");
                navigate("/home"); // <-- Change here: Navigate to /home
            }
        } catch (error: unknown) {
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
        </div>
    );
};

export default Login;
