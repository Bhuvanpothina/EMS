package com.lb.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lb.ems.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
