package com.lb.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lb.ems.model.Salary;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.Date;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    Optional<Salary> findByEmployeeEmployeeIdAndAmountAndEffectiveDate(Integer employeeId, BigDecimal amount, Date effectiveDate);
}

