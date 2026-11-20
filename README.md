# IceCube Neutrino Alerts — PC2 study project

## Overview

An **independent educational implementation** of the IceCube Neutrino Observatory PC2 case (section 7750). It demonstrates a RESTful `POST /api/v1/neutrino-alerts` endpoint with DDD, layered architecture, a CQRS create command, Result, Resource, Assembler, validation, internationalized errors, Spring Data JPA, MySQL persistence, OpenAPI and Git Flow.

**Important:** The assignment requires developing from the **instructor-provided base project**, which has not been supplied here. This repository therefore uses a locally written, illustrative `shared` bounded context and **is not the official base project**. The exam prohibits agentic AI development and requires independent work and incremental commits during the evaluation. This repository is **for practice before the exam, not for submission as exam work**.

## Authorship

- **Author:** Kevin Salazar
- **Student code:** U202417747
- **Practice repository:** https://github.com/AresSalamar/upc-pre-202620-1asi0729-pc2-7750

## Stack and configuration

- Java **27** and Spring Boot **4.1.2** (as stated in the PC2)
- Spring Data JPA, MySQL 8+, Springdoc OpenAPI 3.x
- Maven, port **8080**, MySQL schema **icecube**
- Required environment: `DB_USER`, `DB_PASSWORD`; optional `DB_URL`

Create a MySQL schema if your account lacks privileges to create databases:

```sql
CREATE DATABASE IF NOT EXISTS icecube;
```

Run with Java 27 and Maven installed:

```bash
export DB_USER=root
export DB_PASSWORD='your-local-password'
mvn clean package -DskipTests
mvn spring-boot:run
```

Windows PowerShell alternative: `$env:DB_USER="root"` and `$env:DB_PASSWORD="your-local-password"`.
Do not commit database credentials. The Hibernate `ddl-auto=update` setting is for local practice only.

- Swagger UI: http://localhost:8080/swagger-ui.html
- API: http://localhost:8080/api/v1/neutrino-alerts

## Example request

```http
POST /api/v1/neutrino-alerts
Content-Type: application/json
Accept-Language: en-US
```

```json
{
  "alertCode": "IC-2026-001",
  "detectorId": "550e8400-e29b-41d4-a716-446655440000",
  "followUpStartDate": "2026-11-21",
  "followUpEndDate": "2026-11-25",
  "minimumEnergy": 1.25,
  "maximumEnergy": 20.75,
  "confidenceScore": 0.95,
  "status": "CANDIDATE",
  "scientificNotes": "High-energy event for scientific follow-up"
}
```

Successful creation returns **201** with a generated `id` and all business fields; omitted `status` defaults to `CANDIDATE`. Error responses contain `{"message":"..."}`. Request validation and malformed values return **400**, duplicate `alertCode` returns **409**, and unexpected errors return **500**. Use `Accept-Language: es-PE` for Spanish error messages.

`FollowUpWindow.isWithinWindow()` and `EnergyRange.containsEnergy()` use inclusive bounds; they are implemented even though POST does not call them.

## Project structure

- `shared/application`: generic `Result` success/failure abstraction (study replacement)
- `shared/infrastructure`: JPA physical naming strategy (study replacement)
- `shared/interfaces`: localized exception handling (study replacement)
- `detection/domain`: NeutrinoAlert aggregate, value objects, command and domain repository
- `detection/application`: command service and business outcomes
- `detection/infrastructure`: JPA entity and repository adapter
- `detection/interfaces`: request/resource records, assemblers and controller

See [Architecture](docs/ARCHITECTURE.md). This repository has no GET, PUT, DELETE, CORS or security endpoints.

## Verification scope

The code is intended for Java 27 and Spring Boot 4.1.2; the publishing environment does not include JDK 27, Maven or a running MySQL server, so full application startup and database integration **have not been verified** here.
