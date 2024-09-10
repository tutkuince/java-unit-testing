## Unit tests and Integration tests for Spring Boot App using JUnit5, Mockito and Test Containers

### Unit Test
**Unit testing** involves the testing of each unit or an individual component of the software application.
The purpose is to validate that each unit of the software code performs as expected
**Unit** may be an individiual function, method, procedure, module and object

#### Unit Testing Repository Layer
Spring Boot provides the **@DataJpaTest** annotation to test the persistence layer components that will autoconfigure in-memory embedded database for testing purposes.
By default, it scans for **@Entity** classes and configures Spring Data JPA repositories annotated with **@Repository** annotation.
By default, tests annotated with **@DataJpaTest** are transactional and roll back at the end of each test.

### Integration Test
Integration tests focus on integrating different layers of the application. That also means no mocking is involved.
Basically, we write integration tests for testing a feature which may involve interaction with multiple components. 
