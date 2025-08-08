# Spring Boot Microservices Architecture

This project demonstrates a complete **Spring Boot Microservices** system built using Java and Spring Cloud. It includes three core microservices:

- **Employee Service**
- **Department Service**
- **Organization Service**

All services are registered with **Eureka Service Registry**, and requests are routed through a **Spring Cloud API Gateway**.

---


---

## 🧩 Features

- **Spring Boot Microservices**: Employee, Department, Organization
- **Spring Cloud Components**:
  - **Eureka** (Service Discovery)
  - **API Gateway** (Routing and filtering)
  - **Spring Cloud Config** (Centralized config using Git)
  - **Spring Cloud LoadBalancer** (Client-side load balancing)
  - **Spring Cloud Bus + RabbitMQ** (Dynamic config refresh)
- **Inter-service Communication**:
  - Used **RestTemplate**, **OpenFeign**, and **WebClient**
- **Resilience4j**:
  - Circuit Breaker
  - Retry
  - Rate Limiter
- **Distributed Tracing**:
  - **Spring Micrometer**
  - **Zipkin**
- **Docker**: All services containerized for easy deployment
- **React Frontend**: Simple UI for interacting with services

**Tech Stack**
Backend: Java, Spring Boot, Spring Cloud (Eureka, Gateway, Config, Bus)
Frontend: React.js
Communication: RestTemplate, Feign, WebClient
Messaging: RabbitMQ
Tracing: Zipkin, Spring Micrometer
Resilience: Resilience4j (CircuitBreaker, Retry, RateLimiter)
Config: Centralized Git repository
Deployment: Docker


