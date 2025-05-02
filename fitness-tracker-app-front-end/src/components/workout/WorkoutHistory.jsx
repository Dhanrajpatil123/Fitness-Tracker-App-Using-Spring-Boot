import React, { useState, useEffect } from "react";
import axios from "axios";

const WorkoutHistory = () => {
  const [workouts, setWorkouts] = useState([]);
  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (user) {
      axios
        .get(`http://localhost:5000/api/workouts/${user.id}`)
        .then((response) => setWorkouts(response.data))
        .catch((error) => console.error("Error fetching workouts:", error));
    }
  }, [user]);

  return (
    <div>
      <h2>Workout History</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Exercise</th>
            <th>Sets</th>
            <th>Reps</th>
            <th>Weight (kg)</th>
            <th>Duration (min)</th>
            <th>Notes</th>
          </tr>
        </thead>
        <tbody>
          {workouts.map((workout) => (
            <tr key={workout.id}>
              <td>{workout.exerciseName}</td>
              <td>{workout.sets}</td>
              <td>{workout.reps}</td>
              <td>{workout.weight}</td>
              <td>{workout.duration || "N/A"}</td>
              <td>{workout.notes}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default WorkoutHistory;
