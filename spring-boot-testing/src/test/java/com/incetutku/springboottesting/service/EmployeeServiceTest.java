package com.incetutku.springboottesting.service;

import com.incetutku.springboottesting.exception.ResourceNotFoundException;
import com.incetutku.springboottesting.model.Employee;
import com.incetutku.springboottesting.repository.EmployeeRepository;
import com.incetutku.springboottesting.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        /*employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);*/

        employee = Employee.builder()
                .id(1L)
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for saveEmployee method which throws Exception")
    @Test
    void givenExistingEmail_whenSaveEmployee_thenThrowsResourceNotFoundException() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        // given(employeeRepository.save(employee)).willReturn(employee); -> unnecessary

        // when - action or the behaviour that we are going to test
        assertThrows(ResourceNotFoundException.class, () -> employeeService.saveEmployee(employee));

        // then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .name("Utku")
                .surname("Ince")
                .email("ui@mail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    void givenEmptyEmployeeList_whenGetAllEmployees_thenReturnEmptyEmployeeList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .name("Utku")
                .surname("Ince")
                .email("ui@mail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }
}