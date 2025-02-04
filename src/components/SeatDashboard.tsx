import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import Header from "./Header";

interface Seat {
  officeID: number;
  rowInNumber: number;
  colInNumber: number;
  status: string;
  remoteId: number;
}

interface SeatMatrixProps {
  officeId: number;
  userId: number;
}

const UserDashboard: React.FC<SeatMatrixProps> = () => {
  const { officeId } = useParams<{ officeId: string }>();
  const [seats, setSeats] = useState<Seat[]>([]);
  const [rows, setRows] = useState<number>(0);
  const [cols, setCols] = useState<number>(0);
  const [selectedSeat, setSelectedSeat] = useState<number | null>(null);
  const [newStatus, setNewStatus] = useState<string>("");

  useEffect(() => {
    fetchSeatMatrix();
  }, [officeId]);

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

  const handleSeatClick = (remoteId: number) => {
    setSelectedSeat(remoteId);
  };

  const handleStatusChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setNewStatus(e.target.value);
  };

  const handleSubmitStatus = async (seatId: number) => {
    if (selectedSeat !== null && newStatus) {
      try {
        await axios.put(`http://localhost:9000/updateseats/${seatId}`, {
          status: newStatus,
        });
        fetchSeatMatrix();

        setSelectedSeat(null);
        setNewStatus("");
      } catch (error) {
        console.error("Error updating seat status:", error);
      }
    } else {
      alert("Please select a status before submitting.");
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
              backgroundColor: seat.status === "available" ? "green" : "red",
              color: "white",
              border: "1px solid black",
              height: "50px",
              width: "50px",
              position: "relative",
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
                  onClick={() => handleSubmitStatus(seat.remoteId)}
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
    </div>
  );
};

export default UserDashboard;
