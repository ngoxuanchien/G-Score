import React, { useEffect, useState } from "react";
import axios from "axios";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const Reports = () => {
  const [scores, setScores] = useState([]);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  console.log(process.env.REACT_APP_API_URL);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_API_URL}/api/report/statistics`
        );
        setData(response.data);
      } catch (error) {
        setError("Erorr fetching data from the server");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_API_URL}/api/report/top-10-A`
        );
        setScores(response.data);
      } catch (error) {
        setError("Error fetching data from the server");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  if (!data) {
    return <div>No data</div>;
  }

  const subjects = [
    "dia_li",
    "gdcd",
    "hoa_hoc",
    "lich_su",
    "ngoai_ngu",
    "ngu_van",
    "sinh_hoc",
    "toan",
    "vat_li",
  ];

  const levels = ["A", "B", "C", "D"];

  const chartData = {
    labels: subjects,
    datasets: levels.map((level, index) => ({
      label: level,
      backgroundColor: [
        "rgba(75, 192, 192, 0.8)",
        "rgba(54, 162, 235, 0.8)",
        "rgba(255, 206, 86, 0.8)",
        "rgba(153, 102, 255, 0.8)",
      ][index],
      data: subjects.map((subject) => {
        const stat = data.statistics.find(
          (s) => s.subject === subject && s.level === level
        );
        return stat ? stat.count : 0;
      }),
    })),
  };

  const options = {
    plugins: {
      title: {
        display: true,
        text: "Grades Distribution by Subject",
      },
      tooltip: {
        mode: "index",
        intersect: false,
      },
      legend: {
        position: "top",
      },
    },
    responsive: true,
    scales: {
      x: {
        stacked: false,
      },
      y: {
        stacked: false,
      },
    },
  };

  return (
    <div className="reports-container">
      <h2>Reports</h2>
      <div>
        <h2>Chart</h2>
        <Bar data={chartData} options={options} />
      </div>
      <div>
        <h3>Top 10 student of Group A</h3>
        <table className="scores-table">
          <thead>
            <th>SBD</th>
            <th>Total Score</th>
          </thead>
          <tbody>
            {scores.map((student) => (
              <tr>
                <td>{student.sbd}</td>
                <td>{student.totalScore}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Reports;
