package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.DemoApplication;
import com.example.hibernate.jpa.demo.entity.Address;
import com.example.hibernate.jpa.demo.entity.Course;
import com.example.hibernate.jpa.demo.entity.Passport;
import com.example.hibernate.jpa.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repo;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void someTest(){
        //Database Operation 1 - Retrieve Student
        Student student = em.find(Student.class, 20001L);

        //Database Operation 2 - Retrieve Passport
        Passport passport = student.getPassport();

        //Database Operation 3 - Update  passport
        passport.setNumber("E333333");
        //Database Operation 4 - update passport
        student.setName("Ranga - Updated");
    }

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());

    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent(){
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("student -> {}", passport);
        logger.info("passport -> {}", passport.getStudent());

    }

    @Test
    @Transactional
    public void setAddressDetails(){
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("101", "Street", "Hyderabad"));
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());

    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("courses -> {}", student.getCourses());
    }


}
