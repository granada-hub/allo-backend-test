# Documentation

#### Clone repository

`git clone https://github.com/<your-username>/<repo-name>.git`
`cd <repo-name>`

#### Build Application
`mvn clean install`

#### Run Application
`mvn spring-boot:run`

#### Run Test
`mvn test`

---
#### Endpoint usage
`http://{host}/api/finance/data/{resourceType}`

#### Get Available Currencies
`curl -X GET http://localhost:8080/api/finance/data/availableCurrency`

#### Get Latest Rates (with USD spread calculation)
`curl -X GET http://localhost:8080/api/finance/data/latestRates`

#### Get Historical Rates
`curl -X GET http://localhost:8080/api/finance/data/Historical`

### Notes
* github username : granada-hub
* spread factor : 0.00082

---

##  Architectural Rationale

   1.  The Strategy Pattern was used to avoid a monolithic conditional structure that would become harder to maintain as resource types grow. It improves extensibility by allowing new behaviors to be added without modifying existing code, and it improves maintainability by isolating logic into cohesive, testable units with clear responsibilities.

   2.  Centralizes and encapsulates the construction of a complex, externally configured HTTP client, ensuring consistency, reusability, and strict separation between infrastructure configuration and business logic. It also enforces architectural control that prevents multiple or inconsistent client definitions, which can easily happen with standard @Bean methods in larger applications.

   3.  ApplicationRunner is preferred over @PostConstruct because it executes after the entire Spring ApplicationContext has been fully initialized, ensuring all dependencies such as the WebClient factory, strategy registry, and in-memory store are ready. This makes it safer and more predictable for performing external API calls and complex data ingestion. It also provides better control over startup sequencing, error handling, and aligns with Spring Boot’s recommended approach for application initialization logic.
