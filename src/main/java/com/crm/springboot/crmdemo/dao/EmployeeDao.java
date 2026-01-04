package com.crm.springboot.crmdemo.dao;

import com.crm.springboot.crmdemo.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save (Employee employee);

    void deleteById(int id);
}
