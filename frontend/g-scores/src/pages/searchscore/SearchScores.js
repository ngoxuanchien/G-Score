import React, { useState } from "react";
import axios from "axios";
import "./SearchScore.css";

const SearchScores = () => {
  const [scores, setScores] = useState([]);
  const [registrationNumber, setRegistrationNumber] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [sbd, setSbd] = useState("");
  const [maNN, setMaNN] = useState("");

  const handleSubmit = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_API_URL}/api/students/${registrationNumber}`
      );
      console.log(response.data);
      setScores(response.data.grades);
      setSbd(response.data.sbd);
      setMaNN(response.data.ma_ngoai_ngu);
    } catch (error) {
      setError("An error occurred while fetching the data.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h2>User Registration</h2>
      <div>
        <label>
          <p>Registration Number:</p>
          <input
            className="input"
            type="text"
            value={registrationNumber}
            onChange={(e) => setRegistrationNumber(e.target.value)}
            placeholder="Enter registration number"
          />
        </label>
        <button
          className="submit-button"
          onClick={handleSubmit}
          disabled={loading}
        >
          {loading ? "Loading..." : "Submit"}
        </button>
      </div>
      {error && <div className="error">{error}</div>}
      <h2>Detailed Scores</h2>
      {scores.length > 0 ? (
        <div>
          <p>SBD: {sbd}</p>
          <p>Mã Ngoại Ngữ: {maNN}</p>
          <table className="scores-table">
            <thead>
              <tr>
                <th>Subject</th>
                <th>Score</th>
              </tr>
            </thead>
            <tbody>
              {scores.map((score, index) => (
                <tr key={index}>
                  <td>{score.subject}</td>
                  <td>{score.score}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        !loading && (
          <div>No scores found for the given registration number.</div>
        )
      )}
    </div>
  );
};

export default SearchScores;
