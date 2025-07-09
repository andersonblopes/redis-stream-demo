# 📬 Redis Stream Demo with Spring Boot

This project demonstrates a **multi-module** Spring Boot application using **Redis Streams** to handle asynchronous
communication between services. Built with **Java 21** and **Spring Boot 3.5.x**, the architecture separates concerns
between an API (producer) and a background consumer.

---

## 🧱 Modules

- **`commons/`** – Shared DTOs and utility classes.
- **`producer/`** – A REST API that publishes notification messages to a Redis stream.
- **`consumer/`** – A background service that listens to and processes messages from the Redis stream.

---

## 🚀 Features

- Redis Stream-based message publishing and consuming
- Spring Boot 3.5 with Java 21 support
- Modular Maven project structure
- Clean separation of responsibilities
- Minimal and extensible setup

---

## 🔧 Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring Data Redis
- Redis (Streams)
- Maven (Multi-module)

---

## 🏁 Getting Started

### ✅ Prerequisites

- Java 21+
- Redis installed locally or via Docker
- Maven 3.8+

> 💡 You can start Redis quickly using Docker:

```bash
docker run --name redis -p 6379:6379 redis
```

### 📦 Build the Project

```bash
mvn clean install
```

### ▶️ Run Services

#### Start the Producer API:

```bash
cd producer
mvn spring-boot:run
```

#### Start the Consumer Service (in a new terminal):

```bash
cd consumer
mvn spring-boot:run
```

### 📬 Example Request

#### Send a POST request to publish a message:

```bash
curl -X POST http://localhost:8080/v1/notifications \
-H "Content-Type: application/json" \
-d '{"event":"SUBMITTED", "message":"Hello Redis!"}'
```

The consumer service will log the consumed message.

### 📂 Project Structure

```
redis-stream-demo/
├── commons/ # Shared models and utilities
├── producer/ # REST API that writes to Redis stream
├── consumer/ # Listener service that reads from Redis stream
├── pom.xml # Parent POM for Maven multi-module
└── README.md # This file
```

### 📄 License

MIT – feel free to use, share, and modify.

### 🤝 Contributing

Contributions are welcome! If you find bugs or want to add features, feel free to open issues or submit a pull request.

### 📫 Contact

Feel free to reach out via [GitHub issues](https://www.openai.com)
.
