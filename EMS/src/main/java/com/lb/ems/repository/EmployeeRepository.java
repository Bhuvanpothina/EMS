package com.lb.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lb.ems.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
