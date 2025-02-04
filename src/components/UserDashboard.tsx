import React, { useEffect, useState } from "react";
import axios from "axios";
import Header from "./Header";
import { useNavigate } from "react-router-dom";

interface Seat {
  officeID: number;
  rowInNumber: number;
  colInNumber: number;
  status: string;
  remoteId: number;
}

const UserDashboard: React.FC = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useState<string | null>(null);
  const [officeId] = useState(1); // Default to 1
  const [seats, setSeats] = useState<Seat[]>([]);
  const [rows, setRows] = useState<number>(0);
  const [cols, setCols] = useState<number>(0);
  const [selectedSeat, setSelectedSeat] = useState<number | null>(null);
  const [newStatus, setNewStatus] = useState<string>("");
  const [assignedSeat, setAssignedSeat] = useState<Seat | null>(null);
  const [confirmationMessage, setConfirmationMessage] = useState<string>("");

  useEffect(() => {
    // Fetch userId from localStorage
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(storedUserId);
      fetchAssignedSeat(storedUserId);
    } else {
      console.error("User ID not found in local storage.");
      navigate("/login"); 
    }

    // Fetch seat matrix
    fetchSeatMatrix();

    // Load confirmation message
    const savedConfirmationMessage = localStorage.getItem(
      "confirmationMessage"
    );
    if (savedConfirmationMessage) {
      setConfirmationMessage(savedConfirmationMessage);
    }
  }, [navigate]);

  const fetchSeatMatrix = async () => {
    try {
      const response = await axios.get(
        `http://localhost:9000/seatsfromoffice/${officeId}`
      );
      const seatData: Seat[] = response.data;

      setSeats(seatData);

      // Calculate the number of rows and columns dynamically
      const maxRow = Math.max(...seatData.map((seat) => seat.rowInNumber));
      const maxCol = Math.max(...seatData.map((seat) => seat.colInNumber));
      setRows(maxRow);
      setCols(maxCol);
    } catch (error) {
      console.error("Error fetching seat matrix:", error);
    }
  };

  const fetchAssignedSeat = async (userId: string) => {
    try {
      const response = await axios.get(
        `http://localhost:9000/getAssignedSeat/${userId}`
      );
      if (response.data !== "No seat assigned.") {
        setAssignedSeat(response.data);
        localStorage.setItem(
          "assignedSeatId",
          response.data.remoteId.toString()
        );
        const seat = response.data;
        const message = `You have been assigned to Seat: Row ${seat.rowInNumber}, Column ${seat.colInNumber}.`;
        setConfirmationMessage(message);
        localStorage.setItem("confirmationMessage", message);
      } else {
        setAssignedSeat(null);
        localStorage.removeItem("assignedSeatId");
      }
    } catch (error) {
      console.error("Error fetching assigned seat:", error);
    }
  };

  const handleSeatClick = async (remoteId: number) => {
    if (assignedSeat && remoteId !== assignedSeat.remoteId) {
      alert(
        `You are already assigned to Row ${assignedSeat.rowInNumber}, Column ${assignedSeat.colInNumber}. You cannot select another seat.`
      );
      return;
    }
    setSelectedSeat(remoteId);
  };

  const handleStatusChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setNewStatus(e.target.value);
  };

  const handleSubmitStatus = async () => {
    if (selectedSeat === null || !newStatus) {
      alert("Please select a seat and a status.");
      return;
    }

    try {
      if (newStatus === "reserved") {
        await axios.post(
          `http://localhost:9000/assignSeatToUser/${userId}/${selectedSeat}`
        );
        await axios.put(`http://localhost:9000/updateseats/${selectedSeat}`, {
          status: newStatus,
        });
        fetchAssignedSeat(userId!);

        const seat = seats.find((seat) => seat.remoteId === selectedSeat);
        if (seat) {
          const message = `You have been assigned to Seat: Row ${seat.rowInNumber}, Column ${seat.colInNumber}.`;
          setConfirmationMessage(message);
          localStorage.setItem("confirmationMessage", message);
        }
      } else if (newStatus === "available") {
        await axios.put(`http://localhost:9000/updateseats/${selectedSeat}`, {
          status: newStatus,
        });
        await axios.delete(
          `http://localhost:9000/removeSeatAssignment/${userId}`
        );
        setAssignedSeat(null);
        localStorage.removeItem("confirmationMessage");
        setConfirmationMessage("");
        fetchSeatMatrix();
      }
      setSelectedSeat(null);
      setNewStatus("");
    } catch (error) {
      console.error("Error updating seat status:", error);
    }
  };

  return (
    <div style={{ padding: "10px", maxWidth: "100%", wordWrap: "break-word" }}>
      <Header />
      <h2
        style={{ fontSize: "20px", marginBottom: "20px", textAlign: "center" }}
      >
        Seat Matrix for Office ID: {officeId}
      </h2>
      <div
        style={{
          display: "grid",
          gridTemplateRows: `repeat(${rows}, 50px)`,
          gridTemplateColumns: `repeat(${cols}, 50px)`,
          gap: "10px",
          justifyContent: "center",
        }}
      >
        {seats.map((seat) => (
          <div
            key={seat.remoteId}
            onClick={() => handleSeatClick(seat.remoteId)}
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              backgroundColor:
                seat.remoteId === assignedSeat?.remoteId
                  ? "grey"
                  : seat.status === "available"
                  ? "green"
                  : "red",
              color: "white",
              border: "1px solid black",
              height: "50px",
              width: "50px",
              position: "relative",
              cursor: "pointer",
            }}
          >
            {seat.rowInNumber},{seat.colInNumber}
            {selectedSeat === seat.remoteId && (
              <div
                style={{
                  position: "absolute",
                  top: "60px",
                  left: "50%",
                  transform: "translateX(-50%)",
                  backgroundColor: "white",
                  border: "1px solid black",
                  padding: "5px",
                  zIndex: 1000,
                }}
              >
                <select value={newStatus} onChange={handleStatusChange}>
                  <option value="">Select Status</option>
                  <option value="available">Available</option>
                  <option value="reserved">Reserved</option>
                </select>
                <button
                  onClick={handleSubmitStatus}
                  style={{
                    marginLeft: "10px",
                    padding: "5px 10px",
                    cursor: "pointer",
                  }}
                >
                  Submit
                </button>
              </div>
            )}
          </div>
        ))}
      </div>
      {confirmationMessage && (
        <div
          style={{
            marginTop: "20px",
            textAlign: "center",
            padding: "10px",
            backgroundColor: "#f0f8ff",
            color: "#333",
            borderRadius: "5px",
          }}
        >
          <strong>{confirmationMessage}</strong>
        </div>
      )}
    </div>
  );
};

export default UserDashboard;
