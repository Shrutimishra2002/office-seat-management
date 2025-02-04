import axios from "axios";

const BASE_URL = "http://localhost:9000"; 

const SeatService = {
  // Fetch all offices (same as your Office Service)
  fetchOffices: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/office/list`); // Updated URL
      return response.data;
    } catch (error) {
      console.error("Error fetching offices:", error);
      throw error;
    }
  },

  // Fetch seats of a specific office
  fetchSeats: async (officeId: number) => {
    try {
      console.log(`Fetching seats for office ID: ${officeId}`);
      const response = await axios.get(`${BASE_URL}/office/${officeId}/seats`);
      console.log("Response data:", response.data); 
      return response.data; 
    } catch (error) {
      console.error("Error fetching seats:", error);
      throw error;
    }
  },

  // Request a seat for booking
  requestSeat: async (seatId: number) => {
    try {
      const response = await axios.post(`${BASE_URL}/seat/request/${seatId}`); // Updated URL
      return response.data;
    } catch (error) {
      console.error("Error requesting seat:", error);
      throw error;
    }
  },

  // Add a new seat (as in your original SeatController)
  addSeat: async (officeId: number, location: string, status: string) => {
    try {
      const response = await axios.post(`${BASE_URL}/office/${officeId}/seats/add`, {
        location,
        status
      });
      return response.data;
    } catch (error) {
      console.error("Error adding seat:", error);
      throw error;
    }
  },

  // Update an existing seat (if needed)
  updateSeat: async (seatId: number, location: string, status: string) => {
    try {
      const response = await axios.put(`${BASE_URL}/seat/update/${seatId}`, {
        location,
        status
      });
      return response.data;
    } catch (error) {
      console.error("Error updating seat:", error);
      throw error;
    }
  },

  // Delete a seat (if needed)
  deleteSeat: async (seatId: number) => {
    try {
      const response = await axios.delete(`${BASE_URL}/seat/delete/${seatId}`);
      return response.data;
    } catch (error) {
      console.error("Error deleting seat:", error);
      throw error;
    }
  }

  
};

export default SeatService;
