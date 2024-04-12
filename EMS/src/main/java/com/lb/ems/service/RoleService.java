package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lb.ems.model.Role;
import com.lb.ems.repository.EmployeeRepository;
import com.lb.ems.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	public RoleService(RoleRepository RoleRepository) {
		this.roleRepository = RoleRepository;
	}

	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	public Optional<Role> findRoleById(Integer id) {
		return roleRepository.findById(id);
	}

	public Role saveRole(Role Role) {
		return roleRepository.save(Role);
	}

	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}

	public boolean isRoleUsed(Integer roleId) {
		// This is a simplified example. You need to implement the logic
		// to check if any employees are associated with the roleId.
		return employeeRepository.existsByRoleId(roleId);
	}

}
