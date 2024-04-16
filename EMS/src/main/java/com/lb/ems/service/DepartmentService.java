package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lb.ems.model.Department;
import com.lb.ems.repository.DepartmentRepository;
import com.lb.ems.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
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

    public void deleteDepartment(Integer departmentId) throws Exception {
        if (employeeRepository.findByDepartmentDepartmentId(departmentId).isEmpty()) {
            departmentRepository.deleteById(departmentId);
        } else {
            throw new Exception("Department cannot be deleted as it has linked employees.");
        }
    }
}
