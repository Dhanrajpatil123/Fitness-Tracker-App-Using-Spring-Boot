import React, { useState } from "react";
import axios from "axios";

const WorkoutForm = () => {
  const [exerciseName, setExerciseName] = useState("");
  const [sets, setSets] = useState("");
  const [reps, setReps] = useState("");
  const [weight, setWeight] = useState("");
  const [duration, setDuration] = useState(""); // Added duration for cardio
  const [notes, setNotes] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
      alert("Please log in first");
      return;
    }

    const workoutData = {
      userId: user.id,
      exerciseName,
      sets,
      reps,
      weight,
      duration,
      notes,
    };

    try {
      await axios.post("http://localhost:5000/api/workouts", workoutData);
      alert("Workout logged successfully");
    } catch (error) {
      console.error("Error logging workout:", error);
    }
  };

  return (
    <div>
      <h2>Log a Workout</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Exercise Name</label>
          <input
            type="text"
            className="form-control"
            value={exerciseName}
            onChange={(e) => setExerciseName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Sets</label>
          <input
            type="number"
            className="form-control"
            value={sets}
            onChange={(e) => setSets(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Reps</label>
          <input
            type="number"
            className="form-control"
            value={reps}
            onChange={(e) => setReps(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Weight (kg)</label>
          <input
            type="number"
            className="form-control"
            value={weight}
            onChange={(e) => setWeight(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label>Duration (min) for Cardio</label>
          <input
            type="number"
            className="form-control"
            value={duration}
            onChange={(e) => setDuration(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label>Notes</label>
          <textarea
            className="form-control"
            value={notes}
            onChange={(e) => setNotes(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">Log Workout</button>
      </form>
    </div>
  );
};

export default WorkoutForm;
