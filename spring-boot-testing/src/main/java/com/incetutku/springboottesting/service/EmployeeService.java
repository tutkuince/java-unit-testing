package com.incetutku.springboottesting.service;

import com.incetutku.springboottesting.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
