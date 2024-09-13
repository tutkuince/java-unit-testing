package com.incetutku.springboottesting.controller;

import com.incetutku.springboottesting.model.Employee;
import com.incetutku.springboottesting.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employeeById = employeeService.getEmployeeById(id);
        if (Objects.isNull(employeeById)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        Employee updatableEmployee = employeeService.getEmployeeById(id);
        if (Objects.isNull(updatableEmployee)) {
            return ResponseEntity.notFound().build();
        }
        updatableEmployee.setName(employee.getName());
        updatableEmployee.setSurname(employee.getSurname());
        updatableEmployee.setEmail(employee.getEmail());
        return ResponseEntity.ok(employeeService.updateEmployee(updatableEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
}
