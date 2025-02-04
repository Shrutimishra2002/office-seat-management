/* eslint-disable @typescript-eslint/no-unused-vars */

const BASE_URL = "http://localhost:9000";


export const createOffice = async (name: string, rows: number, columns: number) => {
  try {
    const response = await fetch(`${BASE_URL}/office/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, rows, columns }),  // Sending the data properly
    });

    if (!response.ok) {
      throw new Error("Failed to create office");
    }

    return response.json();  // Return the response as JSON
  } catch (error) {
    console.error("Error fetching offices:", error);
    throw error;
  }
};
export const deleteOffice = async (officeId: number) => {
  try {
    // Ensure the URL format matches the route in your backend
    const response = await fetch(`${BASE_URL}/office/delete${officeId}`, {  // Ensure no colon `:` in the URL
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Failed to delete office");
    }

    return await response.json();
  } catch (error) {
    console.error("Error fetching offices:", error);
    throw error;
  }
};



// Get the list of all offices
export const getOffices = async () => {
  try {
    const response = await fetch(`${BASE_URL}/offices/list`);
    if (!response.ok) {
      throw new Error("Failed to fetch offices");
    }
    const data = await response.json();
    return data; // Assuming the backend returns an array of offices [{id, name}]
  } catch (error) {
    console.error("Error fetching offices:", error);
    throw error;
  }

};



