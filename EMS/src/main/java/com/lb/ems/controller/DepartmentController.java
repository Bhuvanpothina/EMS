package com.lb.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "departments/list";
    }

    @GetMapping("/new")
    public String showDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/form";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Department department = departmentService.findDepartmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("department", department);
        return "departments/form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateDepartment(@ModelAttribute("department") Department department, RedirectAttributes redirectAttributes) {
        departmentService.saveDepartment(department);
        redirectAttributes.addFlashAttribute("successMessage", "Department saved successfully.");
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.deleteDepartment(id);
            redirectAttributes.addFlashAttribute("successMessage", "Department deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete department: " + e.getMessage());
        }
        return "redirect:/departments";
    }
}
