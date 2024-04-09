package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lb.ems.model.Salary;
import com.lb.ems.service.EmployeeService;
import com.lb.ems.service.SalaryService;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

	private final SalaryService salaryService;
	private final EmployeeService employeeService; // To populate the dropdown of employees

	@Autowired
	public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
		this.salaryService = salaryService;
		this.employeeService = employeeService;
	}

	@GetMapping
	public String listSalaries(Model model) {
		model.addAttribute("salaries", salaryService.findAllSalaries());
		return "salaries/list"; // HTML template for listing salaries
	}

	@GetMapping("/new")
	public String showSalaryForm(Model model) {
		model.addAttribute("salary", new Salary());
		model.addAttribute("employees", employeeService.findAllEmployees());
		return "salaries/form"; // HTML form for creating a new salary record
	}

	@PostMapping
	public String saveSalary(@ModelAttribute("salary") Salary salary, BindingResult result) {
		if (result.hasErrors()) {
			return "salaries/form";
		}
		salaryService.saveSalary(salary);
		return "redirect:/salaries";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Salary salary = salaryService.findSalaryById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid salary Id:" + id));
		model.addAttribute("salary", salary);
		model.addAttribute("employees", employeeService.findAllEmployees()); // For employee dropdown
		return "salaries/form"; // HTML form for editing an existing salary record
	}

	@PostMapping("/update/{id}")
	public String updateSalary(@PathVariable("id") Integer id, @ModelAttribute("salary") Salary salary,
			BindingResult result) {
		if (result.hasErrors()) {
			salary.setSalaryId(id);
			return "salaries/form";
		}
		salaryService.saveSalary(salary); // This will update the salary if it exists
		return "redirect:/salaries";
	}

	@GetMapping("/delete/{id}")
	public String deleteSalary(@PathVariable("id") Integer id) {
		salaryService.deleteSalary(id);
		return "redirect:/salaries"; // Redirects back to the salary list
	}
}
