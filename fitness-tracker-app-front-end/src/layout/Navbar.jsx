import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const navigate = useNavigate();

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">Fitness Tracker</Link>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            {!user ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">Login</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/register">Register</Link>
                </li>
              </>
            ) : (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/workout-form">Log Workout</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/workout-history">Workout History</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/goal-tracker">Goal Tracker</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/progress-chart">Progress</Link>
                </li>
                <li className="nav-item">
                  <button
                    className="btn btn-danger"
                    onClick={() => {
                      localStorage.removeItem("user");
                      navigate("/login"); // Redirect to login after logout
                    }}
                  >
                    Logout
                  </button>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
