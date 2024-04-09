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

	public Salary saveSalary(Salary Salary) {
		return salaryRepository.save(Salary);
	}

	public void deleteSalary(Integer id) {
		salaryRepository.deleteById(id);
	}
}
