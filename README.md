# Quote API Project

## Project Overview
This is a simple RESTful API built using Java Spring Boot that provides random inspirational quotes. 
It also implements IP-based rate limiting to prevent abuse of the API.

## Functional Requirements
- Endpoint: `GET /api/quote`
- Returns a random inspirational quote in JSON format:
  ```json
  {
    "quote": "The only way to do great work is to love what you do. - Steve Jobs"
  }
Rate Limiting:

Each IP can make up to 5 requests per minute.

If limit exceeds, returns HTTP 429 Too Many Requests:

json
{
  "error": "Rate limit exceeded. Try again in X seconds."
}
Setup Instructions
Install Java 20 and Maven.

Clone the repository:

git clone <https://github.com/krupa078/quote-api>
cd quote-api
Build and run the project:

mvn clean install
mvn spring-boot:run
API will run on http://localhost:8080

Assumptions / Design Decisions
In-memory rate limiting using Bucket4j.
No database or persistent storage needed.
Thread-safe handling for concurrent requests.

Testing
Example curl command:

curl http://localhost:8080/api/quote
You can also use Postman to test the endpoint.

Backend Endpoint URL
http://localhost:8080/api/quote