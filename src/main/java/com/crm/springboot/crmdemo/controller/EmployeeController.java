package com.crm.springboot.crmdemo.controller;

import com.crm.springboot.crmdemo.entity.Employee;
import com.crm.springboot.crmdemo.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService =theEmployeeService;
    }
    @GetMapping("/list")
    public String listEmployee(Model model){

        List<Employee>  theEmployees = employeeService.findAll();

        model.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employees/add-employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        employeeService.save(employee);

        return "redirect:/employees/list";

    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model)
    {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return"employees/add-employeeform";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id)
    {
       employeeService.deleteById(id);

        return"redirect:/employees/list";
    }
}
