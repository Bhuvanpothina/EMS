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
        System.out.println("Hello showSalaryForm");
        return "salaries/form";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Salary salary = salaryService.findSalaryById(id).orElseThrow(() -> new IllegalArgumentException("Invalid salary Id:" + id));
        model.addAttribute("salary", salary);
        model.addAttribute("employees", employeeService.findAllEmployees());
        return "salaries/form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateSalary(@ModelAttribute("salary") Salary salary, BindingResult result, Model model) {
        System.out.println("Helloe saveOrUpdateSalary");
    	if (result.hasErrors()) {
   
            model.addAttribute("employees", employeeService.findAllEmployees());
            return "salaries/form";
        }
        System.out.println("Saving salary: " + salary); // Debugging log
        salaryService.saveSalary(salary);
        return "redirect:/salaries";
    }


    @GetMapping("/delete/{id}")
    public String deleteSalary(@PathVariable("id") Integer id) {
        salaryService.deleteSalary(id);
        return "redirect:/salaries";
    }
}
