package com.example.hibernate.jpa.demo.repository;

import com.example.hibernate.jpa.demo.entity.Course;
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
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        } else{
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager(){
       Course course1 = new Course("Web Services in 100 Steps");
       em.persist(course1);

       Course course2 = findById(10001L);
       course2.setName("JPA in 50 Steps - Updated");
    }

    public void addHardcodedReviewsForCourse() {
        //Get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());
        //add 2 reviews to it
        Review review1 = new Review("5", "Great Hands-on Stuff");
        Review review2 = new Review("5", "Hatsoff");

        //Setting the relationship
        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);

        //save it to the DB
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        //Get the course 10003
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        for(Review review: reviews){
            //Setting the relationship
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
