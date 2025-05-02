// src/services/api.js
import axios from 'axios';

const API_BASE = 'http://localhost:8080/api';

export const registerUser = (userData) =>
  axios.post(`${API_BASE}/users/register`, userData).then(res => res.data);

export const loginUser = (credentials) =>
  axios.post(`${API_BASE}/users/login`, credentials).then(res => res.data);

// export const addWorkout = (workout) =>
//   axios.post(`${API_BASE}/workouts`, workout).then(res => res.data);

export const getWorkoutsByUser = (userId) =>
  axios.get(`${API_BASE}/workouts/user/${userId}`).then(res => res.data);

// api.js
export const fetchWorkouts = async (userId) => {
  try {
    const response = await fetch(`http://localhost:8080/api/workouts/user/${userId}`);
    if (!response.ok) throw new Error("Failed to fetch workouts");
    return await response.json();
  } catch (error) {
    console.error("API error:", error);
    return [];
  }
};

export const addWorkout = async (workoutData) => {
  try {
    const response = await fetch("http://localhost:8080/api/workouts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(workoutData),
    });
    if (!response.ok) throw new Error("Failed to add workout");
    return await response.json();
  } catch (error) {
    console.error("Add workout error:", error);
    throw error;
  }
};