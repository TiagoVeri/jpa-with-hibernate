package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.DemoApplication;
import com.example.hibernate.jpa.demo.entity.Course;
import com.example.hibernate.jpa.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void all_courses(){
        //"Select c From Course c"
        //1. Use Criteria Builder to create a Criteria Query Returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery <Course> query = em.createQuery(cq.select(courseRoot));
        List <Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_having_100Steps(){
        //"Select c From Course c where name like '%100 Steps'"
        //1. Use Criteria Builder to create a Criteria Query Returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        //4. Add Predicates etc to the Criteria Query
        cq.where(like100Steps);
        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery <Course> query = em.createQuery(cq.select(courseRoot));
        List <Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_without_students(){
        //"Select c From Course c where c.students is empty"
        //1. Use Criteria Builder to create a Criteria Query Returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        //4. Add Predicates etc to the Criteria Query
        cq.where(studentsIsEmpty);
        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery <Course> query = em.createQuery(cq.select(courseRoot));
        List <Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void join(){
        //"Select c From Course c join c.students s"
        //1. Use Criteria Builder to create a Criteria Query Returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery <Course> query = em.createQuery(cq.select(courseRoot));
        List <Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void left_join(){
        //"Select c From Course c join c.students s"
        //1. Use Criteria Builder to create a Criteria Query Returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        //3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. Add Predicates etc to the Criteria Query

        //5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery <Course> query = em.createQuery(cq.select(courseRoot));
        List <Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }
}
