package com.incetutku.springboottesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incetutku.springboottesting.model.Employee;
import com.incetutku.springboottesting.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("Junit test for create employee REST API")
    @Test
    void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
        // given - precondition or setup
        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the output
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(employee.getName())));
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.surname", is(employee.getSurname())));
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @DisplayName("Junit test for Get All Employees REST API")
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
        given(employeeService.getAllEmployees()).willReturn(employeeList);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(employeeList.size())));
    }

    @DisplayName("Junit test for Get Employee By Id REST API")
    @Test
    void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
        given(employeeService.getEmployeeById(1L)).willReturn(employee);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees/{id}", 1L));

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
        given(employeeService.getEmployeeById(0L)).willReturn(null);

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/v1/employees/{id}", 0L));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

}