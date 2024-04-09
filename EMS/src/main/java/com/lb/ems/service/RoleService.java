package com.lb.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lb.ems.model.Role;
import com.lb.ems.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

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
}
