package com.incetutku.springboottesting.integration.testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incetutku.springboottesting.model.Employee;
import com.incetutku.springboottesting.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class EmployeeControllerITest {

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        employee = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("Description")
    @Test
    void isTestContainerRunning() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @DisplayName("Integration test for create employee REST API")
    @Test
    void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        // given - precondition or setup
        // employee
        System.out.println(postgreSQLContainer.getDatabaseName());  // test
        System.out.println(postgreSQLContainer.getUsername());      // test
        System.out.println(postgreSQLContainer.getPassword());      // test
        System.out.println(postgreSQLContainer.getJdbcUrl());      // test


        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the output
        response.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(employee.getName())));
        response.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.surname", is(employee.getSurname())));
        response.andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @DisplayName("Integration test for Get All Employees REST API")
    @Test
    void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeeList() throws Exception {
        // given - precondition or setup
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(Employee.builder()
                .name("Utku")
                .surname("Ince")
                .email("utku@mail.com")
                .build());
        employeeRepository.saveAll(employeeList);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(employeeList.size())));
    }

    @DisplayName("Integration test for Get Employee By Id REST API - Positive Scenario")
    @Test
    void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
        Employee savedEmployee = employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees/{id}", savedEmployee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(employee.getName())));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.surname", is(employee.getSurname())));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @DisplayName("Junit test for Get Employee By Id REST API - Negative Scenario")
    @Test
    void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees/{id}", 0L));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Integration test for Update Employee REST API - Positive Scenario")
    @Test
    void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception {
        // given - precondition or setup
        Employee updatedEmployee = Employee.builder()
                .name("TUTKU")
                .surname("INCE")
                .email("tutku@mail.com")
                .build();
        Employee savedEmployee = employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(put("/api/v1/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedEmployee.getName())))
                .andExpect(jsonPath("$.surname", is(updatedEmployee.getSurname())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
    }

    @DisplayName("Junit test for Update Employee REST API - Negative Scenario")
    @Test
    void givenUpdatedEmployee_whenUpdateEmployee_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        Employee updatedEmployee = Employee.builder()
                .name("TUTKU")
                .surname("INCE")
                .email("tutku@mail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(put("/api/v1/employees/{id}", 0L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Junit test for Delete Employee By Id REST API")
    @Test
    void givenEmployeeId_whenDeleteEmployeeById_thenReturn200() throws Exception {
        // given - precondition or setup
        Employee savedEmployee = employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(delete("/api/v1/employees/{id}", savedEmployee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
