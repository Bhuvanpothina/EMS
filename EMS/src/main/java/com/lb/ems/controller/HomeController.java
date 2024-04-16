package com.lb.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lb.ems.model.Employee;
import com.lb.ems.service.DepartmentService;
import com.lb.ems.service.EmployeeService;
import com.lb.ems.service.RoleService;

@Controller
public class HomeController {

	private final EmployeeService employeeService;

	@Autowired
	public HomeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

//	@GetMapping("/")
//	public String home() {
//		return "home"; // This will resolve to /src/main/resources/templates/home.html
//	}

	@GetMapping("/search")
	public String searchEmployees(@RequestParam Optional<String> name, @RequestParam Optional<Integer> department,
			@RequestParam Optional<Integer> role, Model model) {
		// Convert empty or invalid numbers to null
		Integer departmentId = (department.isPresent() && department.get() > 0) ? department.get() : null;
		Integer roleId = (role.isPresent() && role.get() > 0) ? role.get() : null;

		System.out.println(
				"Searching for name: " + name.orElse("N/A") + ", Department: " + departmentId + ", Role: " + roleId);

		List<Employee> results = employeeService.searchEmployees(name.orElse(""), departmentId, roleId);
		model.addAttribute("employees", results);
		if (results.isEmpty()) {
			model.addAttribute("message", "No employees found.");
		} else {
			model.addAttribute("message", "Found " + results.size() + " employees.");
		}
		return "home"; // or your appropriate view name
	}

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("roles", roleService.findAllRoles());
		return "home"; // Assuming 'home' is your homepage view
	}
}