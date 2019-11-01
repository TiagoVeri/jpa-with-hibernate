package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.DemoApplication;
import com.example.hibernate.jpa.demo.entity.Course;
import com.example.hibernate.jpa.demo.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repo;

    @Test
    public void  findById_CoursePresent(){
        Optional<Course> courseOptional = repo.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void  findById_CourseNotPresent(){
        Optional<Course> courseOptional = repo.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void  playingAroundWithSpringDataRepository(){
        //        Course course = new Course("Microservices in 100 steps");
        //        repo.save(course);
        //        course.setName("Microservices in 100 steps - Updated");
        //        repo.save(course);
        logger.info("Courses -> {}", repo.findAll());
        logger.info("Courses -> {}", repo.count());

    }

    @Test
    public void  pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repo.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repo.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());


    }

    @Test
    public void  findUsingName(){

         logger.info("FindByName -> {}", repo.findByName("JPA in 50 Steps"));


    }
}
