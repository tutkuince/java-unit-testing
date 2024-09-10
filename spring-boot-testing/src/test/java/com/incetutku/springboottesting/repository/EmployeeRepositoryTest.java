package com.incetutku.springboottesting.repository;

import com.incetutku.springboottesting.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @DisplayName("Junit test for save employee operation")
    @Test
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("Junit test for get all employees operation")
    @Test
    void givenEmployeesList_whenFindAll_thenReturnEmployeeList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();

        Employee employee2 = Employee.builder()
                .name("Utku")
                .surname("Ince")
                .email("ui@mail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("Junit test for get employee by id operation")
    @Test
    void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Optional<Employee> employeeFromDB = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeFromDB.isPresent()).isTrue();
        assertThat(employeeFromDB.get()).isNotNull();
        assertThat(employeeFromDB).isNotNull();
    }

    @DisplayName("Junit test for get employee by email operation")
    @Test
    void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Optional<Employee> byEmail = employeeRepository.findByEmail(employee.getEmail());

        // then - verify the output
        assertThat(byEmail.isPresent()).isTrue();
        assertThat(byEmail).isNotNull();
        assertThat(byEmail.get()).isNotNull();
    }
}