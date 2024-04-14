package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lb.ems.model.Salary;
import com.lb.ems.service.EmployeeService;
import com.lb.ems.service.SalaryService;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

	private final SalaryService salaryService;
	private final EmployeeService employeeService;

	@Autowired
	public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
		this.salaryService = salaryService;
		this.employeeService = employeeService;
	}

	@GetMapping
	public String listSalaries(Model model) {
		model.addAttribute("salaries", salaryService.findAllSalaries());
		return "salaries/list";
	}

	@GetMapping("/new")
	public String showSalaryForm(Model model) {
		model.addAttribute("salary", new Salary());
		model.addAttribute("employees", employeeService.findAllEmployees());
		return "salaries/form";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Salary salary = salaryService.findSalaryById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid salary Id:" + id));
		model.addAttribute("salary", salary);
		model.addAttribute("employees", employeeService.findAllEmployees());
		return "salaries/form";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateSalary(@ModelAttribute("salary") Salary salary, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("employees", employeeService.findAllEmployees());
			return "salaries/form";
		}

		try {
			salaryService.saveSalary(salary);
		} catch (IllegalStateException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Duplicate salary record exists.");
			return "redirect:/salaries/form";
		}

		return "redirect:/salaries";
	}

	@GetMapping("/delete/{id}")
	public String deleteSalary(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			salaryService.deleteSalary(id);
			redirectAttributes.addFlashAttribute("successMessage", "Salary deleted successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error occurred while deleting salary.");
		}
		return "redirect:/salaries";
	}
}
