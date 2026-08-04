package com.tns.RestApiHttpMethods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tns.RestApiHttpMethods.entity.Employee;
import com.tns.RestApiHttpMethods.exception.BusinessException;
import com.tns.RestApiHttpMethods.exception.DuplicateResourceException;
import com.tns.RestApiHttpMethods.exception.ResourceNotFoundException;
import com.tns.RestApiHttpMethods.repository.EmployeeRepository;
import com.tns.RestApiHttpMethods.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(Integer id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with ID " + id + " not found"));

    }

    @Override
    public Employee addEmployee(Employee employee) {

        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (employee.getSalary() < 1000) {
            throw new BusinessException("Salary must be at least 1000");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.findByEmail(employee.getEmail())
                .ifPresent(emp -> {
                    if (!emp.getId().equals(id)) {
                        throw new DuplicateResourceException("Email already exists");
                    }
                });

        if (employee.getSalary() < 1000) {
            throw new BusinessException("Salary must be at least 1000");
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);
    }
}