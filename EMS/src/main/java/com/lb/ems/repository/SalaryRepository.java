package com.lb.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lb.ems.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
