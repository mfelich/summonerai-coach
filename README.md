# SummonerAI Coach – Backend Analytics & AI Integration

## Overview
SummonerAI Coach is a backend-driven analytics platform designed to process external data sources and generate contextual insights using AI services.
The system focuses on structured data ingestion, transformation, and analysis, exposing processed results through secure REST APIs for frontend consumption.

The project emphasizes third-party API integration, data processing pipelines, and AI-assisted analysis rather than domain-specific gameplay mechanics.

---

## Core Features
- Integration with external REST APIs for structured data retrieval
- Backend data processing and aggregation
- AI-driven insight generation using OpenAI APIs
- Secure REST API design for frontend consumption
- Separation of data ingestion, processing, and presentation layers

---

## Technology Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

### External Integrations
- Riot Games API (data ingestion)
- OpenAI API (AI-based analysis generation)

### Frontend
- React (data visualization and reporting)

---

## Architecture Overview
The backend is structured around a layered architecture:

- Controller layer – Exposes REST endpoints for processed analytics
- Service layer – Handles business logic, data transformation, and AI orchestration
- Integration layer – Manages communication with external APIs
- Repository layer – Optional persistence and caching of processed data

This design ensures clear responsibility boundaries and simplifies future extension or replacement of external services.

---

## Data Processing Flow
1. External data is fetched from third-party APIs.
2. Raw data is normalized and transformed into internal models.
3. Aggregated statistics are computed in the service layer.
4. AI services are invoked to generate contextual insights based on processed data.
5. Final results are returned to the frontend through REST endpoints.

---

## External API Considerations
- Defensive handling of third-party API limitations and response formats
- Controlled request flow to prevent unnecessary API calls
- Structured mapping between external DTOs and internal domain models

---

## Running the Application

### Prerequisites
- Java 17
- Maven or Gradle
- Valid API keys for external services

### Environment Variables
RIOT_API_KEY=your_riot_api_key  
OPENAI_API_KEY=your_openai_api_key

### Start the Application
mvn spring-boot:run

The backend will be available at:
- API: http://localhost:8080

---

## Testing
- Service-layer logic tested independently from external API calls
- External integrations designed to be mockable for testing purposes

---

## Project Status
This project demonstrates backend integration patterns for external APIs, data processing pipelines, and AI-assisted analysis within a Spring Boot application.

---

## Author
Mirza Felić  
Backend Engineer (Java / Spring)
