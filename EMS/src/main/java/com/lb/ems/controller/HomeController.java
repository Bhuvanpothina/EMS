package com.lb.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lb.ems.model.Employee;
import com.lb.ems.service.EmployeeService;

@Controller
public class HomeController {

	private final EmployeeService employeeService;

	@Autowired
	public HomeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String home() {
		return "home"; // This will resolve to /src/main/resources/templates/home.html
	}

	@GetMapping("/search")
	public String searchEmployees(@RequestParam Optional<String> name, @RequestParam Optional<Integer> department,
			@RequestParam Optional<Integer> role, Model model) {
		List<Employee> results = employeeService.searchEmployees(name.orElse(""), department.orElse(null),
				role.orElse(null));
		model.addAttribute("employees", results);
		return "home"; // Assuming 'home' is your homepage view
	}
}
