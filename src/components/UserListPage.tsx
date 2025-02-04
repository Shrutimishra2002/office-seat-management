// UserListPage.tsx
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const UserListPage = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  // Extract officeId from query parameters
  const { officeId } = useParams<{ officeId: string }>();
  console.log(officeId);

  // Fetch user list
  const fetchUsers = async () => {
    try {
      const response = await fetch("http://localhost:9000/listUsersNotAdmin");
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error("Error fetching user list:", error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleUserClick = (userId: unknown) => {
    // Navigate to SeatDashboard with both userId and officeId
    navigate(`/seat-dashboard/${userId}?officeId=${officeId}`);
  };

  return (
    <div>
      <h1>User List</h1>
      {users.length > 0 ? (
        <ul>
          {users.map((user) => (
            <li
              key={user.remoteId}
              style={{
                margin: "10px 0",
                cursor: "pointer",
                border: "1px solid #ccc",
                padding: "10px",
                borderRadius: "5px",
              }}
              onClick={() => handleUserClick(user.remoteId)}
            >
              <p>
                <strong>
                  {user.firstName} {user.lastName}
                </strong>
              </p>
              <p>Email: {user.email}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>Loading users...</p>
      )}
    </div>
  );
};

export default UserListPage;
