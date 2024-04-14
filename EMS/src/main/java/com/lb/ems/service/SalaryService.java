package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lb.ems.model.Salary;
import com.lb.ems.repository.SalaryRepository;
import java.util.List;
import java.util.Optional;
@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public List<Salary> findAllSalaries() {
        return salaryRepository.findAll();
    }

    public Optional<Salary> findSalaryById(Integer id) {
        return salaryRepository.findById(id);
    }

    public Optional<Salary> findDuplicateSalary(Salary salary) {
        return salaryRepository.findByEmployeeEmployeeIdAndAmountAndEffectiveDate(
                salary.getEmployee().getEmployeeId(),
                salary.getAmount(),
                salary.getEffectiveDate()
        );
    }

    public Salary saveSalary(Salary salary) {
        Optional<Salary> existingSalary = findDuplicateSalary(salary);
        if (existingSalary.isPresent()) {
            // Handle the duplicate case, e.g., throw an exception or do nothing
            throw new IllegalStateException("Duplicate salary record exists.");
        }
        return salaryRepository.save(salary);
    }

    public void deleteSalary(Integer id) {
        salaryRepository.deleteById(id);
    }
}

