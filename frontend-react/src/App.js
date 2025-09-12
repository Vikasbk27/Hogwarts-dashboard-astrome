import React, { useState, useEffect, useRef } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  CartesianGrid,
} from "recharts";

const API = process.env.REACT_APP_API || "http://localhost:8080/api";

const houseIcons = {
  Gryff: "🦁",
  Slyth: "🐍",
  Raven: "🐦",
  Huff: "🦡",
};

export default function App() {
  const [data, setData] = useState([]);
  const [window, setWindow] = useState("all");
  const [live, setLive] = useState(true);
  const eventSourceRef = useRef(null);

  // Fetch leaderboard
  const fetchLeaderboard = async (w = window) => {
    const res = await fetch(`${API}/leaderboard?window=${w}`);
    const json = await res.json();
    const arr = Object.entries(json).map(([house, points]) => ({
      house,
      points,
    }));
    setData(arr);
  };

  // Setup SSE for live updates
  useEffect(() => {
    fetchLeaderboard();

    if (live) {
      eventSourceRef.current = new EventSource(`${API}/stream`);
      eventSourceRef.current.onmessage = (event) => {
      console.log("SSE event received:", event.data);
      fetchLeaderboard();
};

    }

    return () => {
      if (eventSourceRef.current) {
        eventSourceRef.current.close();
      }
    };
  }, [window, live]);

  const toggleLive = () => {
    setLive((prev) => !prev);
  };

  return (
    <div style={{ textAlign: "center", margin: "2rem" }}>
      <h2>🏆 Live Leaderboard</h2>

      {/* Controls */}
      <div style={{ marginBottom: "1rem" }}>
        <button
          onClick={toggleLive}
          style={{
            padding: "10px 20px",
            marginRight: "1rem",
            backgroundColor: live ? "orange" : "green",
            color: "white",
            border: "none",
            borderRadius: "8px",
            cursor: "pointer",
          }}
        >
          {live ? "Stop Updates" : "Start Updates"}
        </button>

        <select
          value={window}
          onChange={(e) => setWindow(e.target.value)}
          style={{ padding: "10px", borderRadius: "8px" }}
        >
          <option value="5m">Last 5 Minutes</option>
          <option value="1h">Last 1 Hour</option>
          <option value="all">All Time</option>
        </select>
      </div>

      {/* Bar Chart */}
      <BarChart
        width={600}
        height={300}
        data={data}
        layout="vertical"
        margin={{ top: 20, right: 30, left: 60, bottom: 20 }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis type="number" />
        <YAxis
          dataKey="house"
          type="category"
          tickFormatter={(house) => `${houseIcons[house]} ${house}`}
        />
        <Tooltip formatter={(value, name, props) => [`${value}`, "count"]} />
        <Legend />
        <Bar dataKey="points" fill="#4A47D5" />
      </BarChart>
    </div>
  );
}
