package com.bhattaditya.sms.controller;

import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents() {
        LOGGER.info("Getting students...");

        return studentService.getStudents();
    }

    @GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("studentId") long studentId) {
        LOGGER.info("Getting student...");

        return studentService.getStudent(studentId);
    }

    @GetMapping(value = "/enrolledStudent/{subject}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enrollment> enrolledStudents(@PathVariable("subject") String subject) {
        return studentService.enrolledStudents(subject);
    }
}
