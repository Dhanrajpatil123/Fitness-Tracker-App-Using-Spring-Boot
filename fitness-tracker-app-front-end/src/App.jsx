import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./layout/Navbar";
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import Dashboard from "./components/Dashboard";
import WorkoutForm from "./components/workout/WorkoutForm";
import WorkoutHistory from "./components/workout/WorkoutHistory";
import GoalTracker from "./components/workout/GoalTracker";
import ProgressChart from "./components/workout/Progress";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container mt-5">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/workout-form" element={<WorkoutForm />} />
          <Route path="/workout-history" element={<WorkoutHistory />} />
          <Route path="/goal-tracker" element={<GoalTracker />} />
          <Route path="/progress-chart" element={<ProgressChart />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
