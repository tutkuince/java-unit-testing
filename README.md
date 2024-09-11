## Unit tests and Integration tests for Spring Boot App using JUnit5, Mockito and Test Containers

### Unit Test
**Unit testing** involves the testing of each unit or an individual component of the software application. <br/>
The purpose is to validate that each unit of the software code performs as expected **Unit** may be an individiual function, method, procedure, module and object <br/>

#### Unit Testing Repository Layer
Spring Boot provides the **@DataJpaTest** annotation to test the persistence layer components that will autoconfigure in-memory embedded database for testing purposes. <br/>
By default, it scans for **@Entity** classes and configures Spring Data JPA repositories annotated with **@Repository** annotation. <br/>
By default, tests annotated with **@DataJpaTest** are transactional and roll back at the end of each test. <br/>

#### Mocking Dependencies using Mockito
**Mockito mock() method** - We can use Mockito class mock() method to create a mock object of a given class or interface. This is the simplest way to mock an object. <br/>
When we want to inject a mocked object into another mocked object, we can use @InjectMocks annotation. <br/>
@InjectMock creates the mock object of the class and injects the mocks that are marked with the annotations @Mock into

### Integration Test
Integration tests focus on integrating different layers of the application. That also means no mocking is involved. <br/>
Basically, we write integration tests for testing a feature which may involve interaction with multiple components. <br/>
