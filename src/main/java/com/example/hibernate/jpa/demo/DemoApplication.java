package com.example.hibernate.jpa.demo;

import com.example.hibernate.jpa.demo.entity.*;
import com.example.hibernate.jpa.demo.repository.CourseRepository;
import com.example.hibernate.jpa.demo.repository.EmployeeRepository;
import com.example.hibernate.jpa.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hands-on Stuff"));
//		reviews.add(new Review("5", "Hatsoff"));
////		studentRepository.saveStudentWithPassport();
//		courseRepository.addReviewsForCourse(10003L, reviews);
//		studentRepository.insertHardcodedStudentAndCourse();
//		studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));
//		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
//
//		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
//		logger.info("All Full Time Employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
//		logger.info("All Part Time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		
	}
}
