package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lb.ems.model.Role;
import com.lb.ems.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public String listRoles(Model model) {
		model.addAttribute("roles", roleService.findAllRoles());
		return "roles/list"; // HTML template for listing roles
	}

	@GetMapping("/new")
	public String showRoleForm(Model model) {
		model.addAttribute("role", new Role());
		return "roles/form"; // HTML form for creating a new role
	}

	@PostMapping
	public String saveRole(@ModelAttribute("role") Role role, BindingResult result) {
		if (result.hasErrors()) {
			return "roles/form";
		}
		roleService.saveRole(role);
		return "redirect:/roles";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Role role = roleService.findRoleById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
		model.addAttribute("role", role);
		return "roles/form"; // HTML form for editing an existing role
	}

	@PostMapping("/update/{id}")
	public String updateRole(@PathVariable("id") Integer id, @ModelAttribute("role") Role role, BindingResult result) {
		if (result.hasErrors()) {
			role.setRoleId(id);
			return "roles/form";
		}
		roleService.saveRole(role); // This will update the role if it exists
		return "redirect:/roles";
	}

	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") Integer id) {
		roleService.deleteRole(id);
		return "redirect:/roles"; // Redirects back to the role list
	}
}
