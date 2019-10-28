package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.entity.Course;
import com.example.hibernate.jpa.demo.entity.Employee;
import com.example.hibernate.jpa.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    //insert an Employee
    public void insert(Employee employee){
        em.persist(employee);
    }

    //retrieve all employees
     public List<Employee> retrieveAllEmployees(){
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
}
