import React from "react";
import { Link } from "react-router-dom";
import "./Sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar-container">
      <h2>Menu</h2>
      <div className="sidebar-menu">
        <Link className="sidebar-link" to="/">
          Dashboard
        </Link>
        <Link className="sidebar-link" to="/search-scores">
          Search Scores
        </Link>
        <Link className="sidebar-link" to="/reports">
          Reports
        </Link>
        <Link className="sidebar-link" to="/settings">
          Settings
        </Link>
      </div>
    </div>
  );
};

export default Sidebar;
