package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lb.ems.model.Employee;
import com.lb.ems.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findEmployeeById(Integer id) {
		return employeeRepository.findById(id);
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}
}
