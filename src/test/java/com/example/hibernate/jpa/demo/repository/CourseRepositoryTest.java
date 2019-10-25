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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repo;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic(){
        Course course = repo.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic(){
        repo.deleteById(10002L);
        assertNull(repo.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic(){
        //get Course.java
        Course course = repo.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
        //update details
        course.setName("JPA in 50 Steps - Updated");
        repo.save(course);
        //checkvalue
        Course course1 = repo.findById(10001L);
        assertEquals("JPA in 50 Steps - Updated", course1.getName());

    }

    @Test
    @DirtiesContext
    public void playWithEntityManager(){
        repo.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        Course course = repo.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        Review review = em.find(Review.class, 50001L);
        logger.info("{}", review.getCourse());
    }
}
