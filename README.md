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
- Docker

---

## 🏁 Getting Started

### ✅ Requirements

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

## 🐳 Running Redis with Docker

To run Redis locally for development and testing, use the following Docker Compose configuration:

### `docker-compose.yml`

```yaml
version: '3.8'

services:
  redis:
    image: redis:7.2
    container_name: redis
    restart: unless-stopped
    ports:
      - "6379:6379"
    volumes:
      - redis-data:/data
    command: redis-server --appendonly yes

volumes:
  redis-data:
```

---

### 🧪 You can start Redis with:

```bash
docker-compose up -d
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

### 📬 Example Usage

1. Start Redis with Docker Compose.
2. Start the consumer service.
3. Start the producer service and send a POST request:

### 📬 Example Request

#### Send a POST request to publish a message:

```bash
curl -X POST http://localhost:8080/notification/v1/send \
-H "Content-Type: application/json" \
-d '{"message":"Hello Redis!"}'
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

Feel free to reach out via [GitHub issues](https://github.com/andersonblopes/redis-stream-demo/issues).
