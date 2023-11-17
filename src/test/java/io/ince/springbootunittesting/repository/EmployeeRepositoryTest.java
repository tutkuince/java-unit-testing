package io.ince.springbootunittesting.repository;

import io.ince.springbootunittesting.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Tutku")
                .lastName("Ince")
                .email("tince@mail.com")
                .build();
        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}