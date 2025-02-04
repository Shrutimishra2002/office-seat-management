/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  createOffice,
  deleteOffice,
  getOffices,
} from "../services/officeService";
import "../styles/AdminDashboard.css";
import Header from "./Header";

const AdminDashboard = () => {
  const [officeName, setOfficeName] = useState<string>("");
  const [rows, setRows] = useState<number | string>(""); 
  const [columns, setColumns] = useState<number | string>(""); 
  const [message, setMessage] = useState<string>("");
  const [offices, setOffices] = useState<{ id: number; name: string }[]>([]); 
  const navigate = useNavigate();
  const [username, setUsername] = useState<string>("");

  // Fetch the list of offices on component load
  useEffect(() => {
    const storeUsername = localStorage.getItem("username");

    if (storeUsername) {
      setUsername(storeUsername);
    }
    fetchOfficeList();
  }, []);

  const fetchOfficeList = async () => {
    try {
      const officeList = await getOffices();
      setOffices(officeList);
    } catch (error) {
      setMessage("Failed to fetch office list.");
    }
  };

  const handleCreateOffice = async () => {
    const rowsNum = Number(rows);
    const columnsNum = Number(columns);

    if (!officeName || rowsNum <= 0 || columnsNum <= 0) {
      setMessage(
        "All fields are required, and rows/columns must be greater than 0."
      );
      return;
    }

    try {
      await createOffice(officeName, rowsNum, columnsNum);
      setMessage("Office created successfully!");
      setOfficeName("");
      setRows("");
      setColumns("");
      fetchOfficeList();
    } catch (error) {
      setMessage("Failed to create office.");
    }
  };

  const handleDeleteOffice = async (officeId: number) => {
    try {
      await deleteOffice(officeId);
      setMessage("Office deleted successfully!");
      fetchOfficeList();
    } catch (error) {
      setMessage("Failed to delete office.");
    }
  };

  const handleOfficeClick = (officeId: number) => {
    navigate(`/seat-dashboard/${officeId}`);
  };

  return (
    <div className="admin-dashboard">
      <Header />

      <div className="main-content">
        <h2>Admin Dashboard</h2>
        <div className="office-form">
          <input
            type="text"
            value={officeName}
            onChange={(e) => setOfficeName(e.target.value)}
            placeholder="Enter office name"
            className="input-field"
          />
          <input
            type="number"
            value={rows}
            onChange={(e) => setRows(Number(e.target.value))}
            placeholder="Enter number of rows"
            className="input-field"
          />
          <input
            type="number"
            value={columns}
            onChange={(e) => setColumns(Number(e.target.value))}
            placeholder="Enter number of columns"
            className="input-field"
          />
          <button onClick={handleCreateOffice} className="create-button">
            Create Office
          </button>
        </div>
        {message && <p className="message">{message}</p>}
        <hr />
        <h3>Office List</h3>
        <ul>
          {offices.length > 0 ? (
            offices.map((office) => (
              <li key={office.id} className="office-item">
                <span
                  className="office-name"
                  onClick={() => handleOfficeClick(office.id)}
                >
                  {office.name}
                </span>
                <button
                  onClick={() => handleDeleteOffice(office.id)} 
                  className="delete-button"
                >
                  Delete
                </button>
              </li>
            ))
          ) : (
            <p>No offices available.</p>
          )}
        </ul>
      </div>
    </div>
  );
};

export default AdminDashboard;
