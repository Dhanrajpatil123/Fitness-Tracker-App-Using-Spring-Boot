import React from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  // Check if user is logged in
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) {
    navigate("/login");
  }

  return (
    <div>
      <h2>Welcome {user?.name}</h2>
      <p>You're logged in. What would you like to do today?</p>
      <div>
        <button className="btn btn-primary m-2" onClick={() => navigate("/workout-form")}>
          Log Workout
        </button>
        <button className="btn btn-primary m-2" onClick={() => navigate("/workout-history")}>
          View Workout History
        </button>
        <button className="btn btn-primary m-2" onClick={() => navigate("/progress")}>
          View Progress
        </button>
      </div>
    </div>
  );
};

export default Dashboard;
