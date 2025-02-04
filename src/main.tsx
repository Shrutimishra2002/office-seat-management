// src/main.tsx
import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import Login from "./components/Login";
import Signup from "./components/Signup";
import AdminDashboard from "./components/AdminDashboard";
// import CreateOffice from "./components/CreateOffice"; // Import CreateOffice component
// import SeatReview from "./components/SeatReview";
// import SeatReview from "./components/SeatReview";
import SeatDashboard from "./components/SeatDashboard";
import UserDashboard from "./components/UserDashboard";
import UserListPage from "./components/UserListPage";

// User dashboard component

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        {/* <Route path="/admin" element={<AdminLogin />} />
        <Route path="/user-options" element={<UserOptions />} /> */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />

        {/* <Route path="/create-office" element={<CreateOffice />} /> */}
        {/* <Route path="/seat-review" element={<SeatReview />} /> */}

        <Route path="/user-dashboard" element={<UserDashboard />} />

        {/* <Route path="/seat-dashboard/:userId" element={<SeatDashboard />} /> */}
        <Route path="/seat-dashboard/:officeId" element={<SeatDashboard />} />

        {/* 
<Route
          path="/seat-dashboard/${userId}?officeId=${officeId}"
          component={<SeatDashboard />}
        /> */}
        {/* <Route path="/user-list/:officeId" element={<UserListPage />} /> */}
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
