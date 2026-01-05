package com.crm.springboot.crmdemo.dao;

import com.crm.springboot.crmdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
