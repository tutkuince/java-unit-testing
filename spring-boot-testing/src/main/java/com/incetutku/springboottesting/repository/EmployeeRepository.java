package com.incetutku.springboottesting.repository;

import com.incetutku.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // Define custom query using JPQL with index params
    @Query("select e from Employee e where e.name = ?1 and e.surname = ?2")
    Employee findByJPQL(String name, String surname);
}
