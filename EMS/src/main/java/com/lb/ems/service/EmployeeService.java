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

	public List<Employee> searchEmployees(String name, Integer departmentId, Integer roleId) {
		  if (departmentId == null || departmentId == -1) {
		        departmentId = null;  // Ensure null is passed if department is not selected
		    }
		    if (roleId == null || roleId == -1) {
		        roleId = null;  // Ensure null is passed if role is not selected
		    }
		return employeeRepository.findByNameContainingAndDepartmentDepartmentIdAndRoleRoleId(name, departmentId, roleId);
	}

}
