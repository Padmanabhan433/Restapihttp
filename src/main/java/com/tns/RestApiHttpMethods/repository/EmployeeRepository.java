package com.tns.RestApiHttpMethods.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.RestApiHttpMethods.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

}