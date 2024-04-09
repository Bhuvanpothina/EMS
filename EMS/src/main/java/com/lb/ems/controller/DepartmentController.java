package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lb.ems.model.Department;
import com.lb.ems.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping
	public String listDepartments(Model model) {
		model.addAttribute("departments", departmentService.findAllDepartments());
		return "departments/list"; // HTML template for listing departments
	}

	@GetMapping("/new")
	public String showDepartmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "departments/form"; // HTML form for creating a new department
	}

	@PostMapping
	public String saveDepartment(@ModelAttribute("department") Department department, BindingResult result) {
		if (result.hasErrors()) {
			return "departments/form";
		}
		departmentService.saveDepartment(department);
		return "redirect:/departments";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Department department = departmentService.findDepartmentById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
		model.addAttribute("department", department);
		return "departments/form"; // HTML form for editing an existing department
	}

	@PostMapping("/update/{id}")
	public String updateDepartment(@PathVariable("id") Integer id, @ModelAttribute("department") Department department,
			BindingResult result) {
		if (result.hasErrors()) {
			department.setDepartmentId(id);
			return "departments/form";
		}
		departmentService.saveDepartment(department); // This will update the department if it exists
		return "redirect:/departments";
	}

	@GetMapping("/delete/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		departmentService.deleteDepartment(id);
		return "redirect:/departments"; // Redirects back to the department list
	}
}
