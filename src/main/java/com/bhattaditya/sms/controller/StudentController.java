package com.bhattaditya.sms.controller;

import com.bhattaditya.sms.constants.SMSConstants;
import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/enrolledStudents/{subject}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PutMapping(value = "enroll/{enrollId}/course/{courseId}/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Enrollment updateCourse(@PathVariable("enrollId") long enrollId, @PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId) {
        LOGGER.info("Updating enrolled course...");

        return studentService.updateCourse(enrollId, courseId, studentId);
    }

    @DeleteMapping(value = "/{studentId}")
    public String removeStudent(@PathVariable("studentId") long studentId) {
        LOGGER.info("Removing student...");
        studentService.removeStudent(studentId);

        return SMSConstants.REMOVED_STUDENT;
    }

    @DeleteMapping(value = "/withdrawCourse/{enrollmentId}")
    public String withdrawName(@PathVariable("enrollmentId") long enrollmentId) {
        LOGGER.info("withdraw enroll course...");
        studentService.withdrawName(enrollmentId);

        return SMSConstants.UPDATED_COURSE;
    }
}
