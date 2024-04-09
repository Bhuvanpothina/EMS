package com.lb.ems.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lb.ems.model.Employee;
import com.lb.ems.service.DepartmentService;
import com.lb.ems.service.EmployeeService;
import com.lb.ems.service.RoleService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService; // To populate departments in the form
	private final RoleService roleService; // To populate roles in the form

	@Autowired
	public EmployeeController(EmployeeService employeeService, DepartmentService departmentService,
			RoleService roleService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.roleService = roleService;
	}

	@GetMapping
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.findAllEmployees());
		return "employees/list";
	}

	@GetMapping("/new")
	public String showEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("roles", roleService.findAllRoles());
		return "employees/form";
	}

	@PostMapping
	public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			return "employees/form";
		}
		if (employee.getHireDate() == null) {
			employee.setHireDate(new Date()); // Set the current date if hireDate is not provided
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Employee employee = employeeService.findEmployeeById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		model.addAttribute("employee", employee);
		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("roles", roleService.findAllRoles());
		return "employees/form";
	}

	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") Integer id, @ModelAttribute("employee") Employee employee,
			BindingResult result) {
		if (result.hasErrors()) {
			employee.setEmployeeId(id);
			return "employees/form";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
}
