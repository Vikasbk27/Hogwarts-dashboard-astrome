

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
* 🐳 **Docker support** for easy setup

---

## 🌱 Branches

This repo has multiple branches:

* **`main`** → Standard setup (manual install: Java, Node, Python, Postgres)
* **`docker`** → Full **Docker Compose setup** (backend, frontend, PostgreSQL, Python generator)

👉 If you want to **run everything with Docker**, switch to the `docker` branch:

```bash
git checkout docker
```

---

## 🛠️ Prerequisites (Manual Setup)

If you’re using the `main` branch, make sure you have:

* **Java 21+** → for Spring Boot backend
* **Maven 3+** → to build/run Spring Boot
* **Node.js 18+ & npm** → for React frontend
* **Python 3.8+** → for the data generator script
* **PostgreSQL + pgAdmin** → to store events

---

## 🗄️ Database Setup (PostgreSQL + pgAdmin)

1. Install **PostgreSQL** and **pgAdmin**.

2. Create a new database (e.g., `hogwarts`).

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

4. Run the backend once → tables will be auto-created by JPA (`Event` entity).

---

## 🚀 Running the Project (Manual)

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Vikasbk27/Hogwarts-dashboard-astrome.git
cd Hogwrats-dashboard
```

### 2️⃣ Start Backend (Spring Boot)

```bash
cd springboot-backend
mvn spring-boot:run
```

Backend → [http://localhost:8080](http://localhost:8080)

### 3️⃣ Start Frontend (React)

```bash
cd ../frontend-react
npm install
npm start
```

Frontend → [http://localhost:3000](http://localhost:3000)

### 4️⃣ Start Data Generator (Python)

```bash
python data_gen_client.py
```

---

## 🐳 Running with Docker (docker-setup branch)

If you want **everything containerized**:

### 1️⃣ Switch to docker branch

```bash
git checkout docker
```

### 2️⃣ Build & start services

```bash
docker-compose up --build
```

This will start:

* **Backend** → `http://localhost:8080`
* **Frontend** → `http://localhost:3000`
* **Postgres DB** → exposed on port `5432`
* **Python Data Generator** → runs automatically and feeds events

### 3️⃣ Check running containers

```bash
docker ps
```

### 4️⃣ Stop services

```bash
docker-compose down
```

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


