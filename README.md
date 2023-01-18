# The project
This project aims to register and validate customer documentation. The technologies used to carry out the project were:

* Java JDK 17
* Spring Boot 2x - spring ecosystem
* MVC architecture
* Rest API
* Database in H2 memory
* API documentation with OpenAPI - Swagger ui

# Execution Mode

### Step by step to run the project

* Run the gradle build (./gradlew clean build)
* Run the compiled java binary (java -jar build/libs/management-0.0.1-SNAPSHOT.jar)
* The application will run on port 8080 (http://localhost:8080)
* The POST API for creating and validating the client is found at path (http://localhost:8080/v1/customer)
* We can see more details of API call on OpenAPI - Swagger in the following route (http://localhost:8080/swagger-ui/index.html#)
* All data registered via API can be viewed in the H2 database as soon as the application is executed (http://localhost:8080/h2-console). Note: This is a common relational database, we can do SQL commands normally.