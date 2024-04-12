package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "roles/list";
	}

	@GetMapping("/new")
	public String showRoleForm(Model model) {
		model.addAttribute("role", new Role());
		return "roles/form";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Role role = roleService.findRoleById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
		model.addAttribute("role", role);
		return "roles/form";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateRole(@ModelAttribute("role") Role role, BindingResult result) {
		if (result.hasErrors()) {
			return "roles/form";
		}
		roleService.saveRole(role);
		return "redirect:/roles";
	}

	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (roleService.isRoleUsed(id)) {
			// Notify user that role is in use and cannot be deleted
			redirectAttributes.addFlashAttribute("errorMessage",
					"This role is currently assigned to one or more employees and cannot be deleted.");
			return "redirect:/roles";
		} else {
			roleService.deleteRole(id);
			redirectAttributes.addFlashAttribute("successMessage", "Role successfully deleted.");
			return "redirect:/roles";
		}
	}
}
