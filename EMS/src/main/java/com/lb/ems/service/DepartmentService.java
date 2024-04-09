package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lb.ems.model.Department;
import com.lb.ems.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}

	public Optional<Department> findDepartmentById(Integer id) {
		return departmentRepository.findById(id);
	}

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}
}
