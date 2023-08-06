package com.bhattaditya.sms.controller;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.service.CourseService;
import com.bhattaditya.sms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

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
        LOGGER.info("Enrolled students...");

        return studentService.enrolledStudents(subject);
    }

    @GetMapping(value = "/courses/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> studentCourses(@PathVariable("email") String email) {
        LOGGER.info("Student Courses...");

        return studentService.studentCourses(email);
    }

    @PostMapping(value = "/enroll/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Enrollment enrollStudent(@Valid @PathVariable("courseId") long courseId, @RequestBody Student student) {
        LOGGER.info("Enroll student...");

        return studentService.enrollStudent(courseId, student);
    }

    // will implement later
    @PutMapping(value = "updateCourse/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollment updateCourse(@PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId) {
        Student student = getStudent(studentId);
        Course course =  courseService.getCourse(courseId);

        return null;
    }
}
