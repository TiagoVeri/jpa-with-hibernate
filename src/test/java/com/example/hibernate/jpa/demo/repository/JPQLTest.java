package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.DemoApplication;
import com.example.hibernate.jpa.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic(){
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select c From Course.java c -> {}", resultList);
    }

    @Test
    public void jpql_typed(){
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List <Course>resultList = query.getResultList();
        logger.info("Select c From Course.java c -> {}", resultList);
    }

    @Test
    public void jpql_where(){
        TypedQuery<Course> query =
                em.createNamedQuery("query_get_100_Step_courses", Course.class);
        List <Course>resultList = query.getResultList();
        logger.info("Select c From Course.java c where name like '%100 Steps -> {}", resultList);
    }


}
