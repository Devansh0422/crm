package com.crm.springboot.crmdemo.dao;

import com.crm.springboot.crmdemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    // define field for entity manager
    private EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public EmployeeDaoJpaImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        //return an executed query
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {

        //return employee
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee save(Employee employee) {

        //save employee
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {

        //find the employee first
        Employee employee = entityManager.find(Employee.class,id);

        // delete the employee
        entityManager.remove(employee);

    }
}
