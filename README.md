# [Task_Management]

Spring boot backend for oasis management fullstack developer asssessement(spring boot and angular) 

### System Stack
- Java
- Spring-boot
- Docker and Docker Compose
- Postgres

### Notable Libraries
- Mapstruct
- Lombok
- Hibernate
- Flyway

### Application port
- Refer to properties.yml: 8282

### API Documentation
- Springdoc Open-Api **[View URL](http://localhost:8282/finspeed-api-docs)**
- Swagger UI **[View URL](http://localhost:8282/swagger-ui/index.html)**

## System Architecture
We are building this application as a monolith with inspiration for loose coupling from Spring Modulith.

We should aim to maintain loosely coupled packages who communicate via thier managers. For each component/microservice, only data, and rest package are exposed. 
Data holds representation(DTO in this case) of entity for cross package communication. Rest contains two packages: dto and resource. Dto serves for taking in request while resource serve as resource(response)


**Component Structure**

tasks (Microservice)
| core
| | taskManagement (Manager takes care on one specific logical Part/Entity of the component)
| | | data (Internal Objects)
| | | database (Entites and Repository)
| | | dataMapper (MapStruct based Mappers)
| | | exception (Internal Exceptions)
| | | rest (Dto and Resources)
| | | DataService (Convert Rest/Internal Object)
| | | Manager (Implemented Logic)
| restController
| | TaskController (API Endpoints)

Logical Flow of an incoming API Request
1. RestController
2. DataService
3. Manager
4. Repository
 
