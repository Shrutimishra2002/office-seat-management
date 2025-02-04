// src/services/api.ts
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:9000", // Base URL for your backend
  headers: {
    "Content-Type": "application/json",
  },
});

// Example of a custom request to handle JWTs
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

export default api;
