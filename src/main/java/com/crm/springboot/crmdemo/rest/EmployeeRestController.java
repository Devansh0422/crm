package com.crm.springboot.crmdemo.rest;

import com.crm.springboot.crmdemo.entity.Employee;
import com.crm.springboot.crmdemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private EmployeeService employeeService;

  private JsonMapper jsonMapper;

    // quick and dirty: inject employee dao (use constructor injection)
    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper)
    {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    // expose "/employees" and return a list of employee
    @GetMapping("/employees")
    public List<Employee> findAll()
    {
      return  employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId)
    {
      Employee employee = employeeService.findById(employeeId);
      if(employee == null)
      {
        throw new RuntimeException("Employee id not found:"+employeeId);
      }
      return employee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee)
    {
      employee.setId(0);
      return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public  Employee updateEmployee(@RequestBody Employee employee)
    {
      return employeeService.save(employee);
    }

    @PatchMapping("/employees/{employeeId}")
    public  Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayLoad){
      Employee employee = employeeService.findById(employeeId);
      if(employee == null)
      {
        throw new RuntimeException("Employee id not found:"+employeeId);
      }
      if(patchPayLoad.containsKey("id"))
      {
        throw new RuntimeException("Employee id not allowed in requestBody:"+employeeId);
      }

      Employee patchEmployee = jsonMapper.updateValue(employee,patchPayLoad);
      return employeeService.save(patchEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId ){
      Employee employee = employeeService.findById(employeeId);
      if(employee == null)
      {
        throw new RuntimeException("Employee id not found:"+employeeId);
      }
       employeeService.deleteById(employeeId);
      return "Deleted Employee Id :"+ employeeId;
    }
}
