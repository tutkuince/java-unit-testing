package com.incetutku.springboottesting.repository;

import com.incetutku.springboottesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
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

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("Junit test for save employee operation")
    @Test
    void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject() {
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
        Employee employee2 = Employee.builder()
                .name("Utku")
                .surname("Ince")
                .email("ui@mail.com")
                .build();

        employeeRepository.save(employee);
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
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Optional<Employee> byEmail = employeeRepository.findByEmail(employee.getEmail());

        // then - verify the output
        assertThat(byEmail.isPresent()).isTrue();
        assertThat(byEmail).isNotNull();
        assertThat(byEmail.get()).isNotNull();
    }

    @DisplayName("Junit test for update employee operation")
    @Test
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("tutku@mail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("tutku@mail.com");
    }

    @DisplayName("JUnit test for delete employee operation")
    @Test
    void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        employeeRepository.delete(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(optionalEmployee).isEmpty();
    }

    @DisplayName("JUnit test for Custom Query using JPQL with index")
    @Test
    void givenNameAndSurname_whenFindByJPQL_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);
        String name = "Tutku";
        String surname = "Ince";

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL(name, surname);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo(name);
        assertThat(savedEmployee.getSurname()).isEqualTo(surname);
    }

    @DisplayName("JUnit test for Custom Query using JPQL with named params")
    @Test
    void givenNameAndSurname_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);
        String name = "Tutku";
        String surname = "Ince";

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(name, surname);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo(name);
        assertThat(savedEmployee.getSurname()).isEqualTo(surname);
    }

    @DisplayName("JUnit test for Custom Query using Native SQL with index")
    @Test
    void givenNameAndSurname_whenFindByNativeSQL_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);
        String name = "Tutku";
        String surname = "Ince";

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(name, surname);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo(name);
        assertThat(savedEmployee.getSurname()).isEqualTo(surname);
    }

    @DisplayName("JUnit test for Custom Query using Native SQL with index")
    @Test
    void givenNameAndSurname_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
        employeeRepository.save(employee);
        String name = "Tutku";
        String surname = "Ince";

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(name, surname);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo(name);
        assertThat(savedEmployee.getSurname()).isEqualTo(surname);
    }
}