package com.tns.RestApiHttpMethods.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tns.RestApiHttpMethods.entity.Employee;

public interface EmployeeService {

    Page<Employee> getAllEmployees(Pageable pageable);

    Employee getEmployeeById(Integer id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);

}
