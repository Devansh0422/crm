package com.crm.springboot.crmdemo.service;

import com.crm.springboot.crmdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save (Employee employee);

    void deleteById(int id);
}
