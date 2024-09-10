package com.incetutku.springboottesting.repository;

import com.incetutku.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // Define custom query using JPQL with index params
    @Query("select e from Employee e where e.name = ?1 and e.surname = ?2")
    Employee findByJPQL(String name, String surname);

    // Define custom query using JPQL with name params
    @Query("select e from Employee e where e.name = :name and e.surname = :surname")
    Employee findByJPQLNamedParams(@Param("name") String name, @Param("surname") String surname);

    // Define custom query using Native SQL with index params
    @Query(value = "select * from empoloyees e where e.name = ?1 and e.surname = ?2", nativeQuery = true)
    Employee findByNativeSQL(String name, String surname);

    // Define custom query using Native SQL with index params
    @Query(value = "select * from empoloyees e where e.name = :name and e.surname = :surname", nativeQuery = true)
    Employee findByNativeSQLNamedParams(@Param("name") String name, @Param("surname") String surname);
}
