
# 🏰 Hogwarts Dashboard

A full-stack project that simulates a **live Hogwarts house points leaderboard** ⚡.
It uses **Spring Boot (backend)**, **React (frontend)**, and a **Python data generator** for streaming events in real-time.

---

## 📌 Features

* 📊 Real-time Hogwarts house leaderboard
* 🐍 Python script to generate live data
* 🌐 REST API + Server Sent Events (SSE) for updates
* 🎨 React + Recharts for an interactive UI
* 💾 PostgreSQL for persistence

---

## 🛠️ Prerequisites

Before running, make sure you have the following installed:

* **Java 21+** → for Spring Boot backend
* **Maven 3+** → to build/run Spring Boot
* **Node.js 18+ & npm** → for React frontend
* **Python 3.8+** → for the data generator script
* **PostgreSQL + pgAdmin** → to store events

---

## 🗄️ Database Setup (PostgreSQL + pgAdmin)

1. Install **PostgreSQL** and **pgAdmin**.

2. Open pgAdmin and create a new database (e.g., `hogwarts`).

3. Update your `application.yml` in `springboot-backend/src/main/resources/` with DB credentials:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/hogwarts
       username: your_pg_username
       password: your_pg_password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```

   > ⚠️ Replace `your_pg_username` and `your_pg_password` with your own.

4. Run the backend once → tables will be auto-created by JPA (`Event` entity).

---

## 🚀 Running the Project

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Vikasbk27/Hogwarts-dashboard-astrome.git
cd Hogwrats-dashboard
```

---

### 2️⃣ Start Backend (Spring Boot)

```bash
cd springboot-backend
mvn spring-boot:run
```

Backend runs at → **[http://localhost:8080](http://localhost:8080)**

---

### 3️⃣ Start Frontend (React)

```bash
cd ../frontend-react
npm install
npm start
```

Frontend runs at → **[http://localhost:3000](http://localhost:3000)**

---

### 4️⃣ Start Data Generator (Python)

Open a new terminal and run:

```bash
python data_gen_client.py
```

This script will continuously generate random house points and send them to the backend.

---

## 📡 API Endpoints

* `POST /api/ingest` → Ingest a new event
* `GET /api/leaderboard?window=5m|1h|all` → Fetch leaderboard
* `GET /api/stream` → Live updates via SSE

---

## 🎨 UI Features

* Dropdown to select time window: `5m | 1h | All`
* Start/Stop button to toggle live updates
* Interactive **bar chart** (hover shows counts per house)
* Houses represented with icons 🦁 🐍 🐦 🦡

---



## 👨‍💻 Author

* Built by **Vikas Bk** 🚀


