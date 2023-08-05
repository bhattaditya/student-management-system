package com.bhattaditya.sms;

import com.bhattaditya.sms.repository.CourseRepository;
import com.bhattaditya.sms.repository.EnrollmentRepository;
import com.bhattaditya.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

//	@Autowired
//	CourseRepository courseRepository;
//
//	@Autowired
//	EnrollmentRepository enrollmentRepository;
//
//	@Autowired
//	StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(courseRepository.findAll());
//		System.out.println(studentRepository.findAll());
//		System.out.println(enrollmentRepository.findAll());
	}
}
