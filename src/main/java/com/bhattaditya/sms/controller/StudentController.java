package com.bhattaditya.sms.controller;

import com.bhattaditya.sms.constants.SMSConstants;
import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> getStudents() {
        LOGGER.info("Getting students...");

        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") long studentId) {
        LOGGER.info("Getting student...");

        return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "/enrolledStudents/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Enrollment>> enrolledStudents(@PathVariable("courseId") long courseId) {
        LOGGER.info("Enrolled students...");

        return new ResponseEntity<>(studentService.enrolledStudents(courseId), HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> studentCourses(@PathVariable("email") String email) {
        LOGGER.info("Student Courses...");

        return new ResponseEntity<>(studentService.studentCourses(email), HttpStatus.OK);
    }

    @PostMapping(value = "/enroll/{courseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> enrollStudent(@Valid @PathVariable("courseId") long courseId, @RequestBody Student student) {
        LOGGER.info("Enroll student...");

        Enrollment enrollment =  studentService.enrollStudent(courseId, student);
        String message = "Enrollment id: " + enrollment.getId();
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping(value = "enroll/{enrollId}/course/{courseId}/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enrollment> updateCourse(@PathVariable("enrollId") long enrollId, @PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId) {
        LOGGER.info("Updating enrolled course...");

        return new ResponseEntity<>(studentService.updateCourse(enrollId, courseId, studentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity<String> removeStudent(@PathVariable("studentId") long studentId) {
        LOGGER.info("Removing student...");
        studentService.removeStudent(studentId);

        return new ResponseEntity<>(SMSConstants.REMOVED_STUDENT, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/withdrawCourse/{enrollmentId}")
    public ResponseEntity<String> withdrawName(@PathVariable("enrollmentId") long enrollmentId) {
        LOGGER.info("withdraw enroll course...");
        studentService.withdrawName(enrollmentId);

        return new ResponseEntity<>(SMSConstants.WITHDRAW_COURSE, HttpStatus.NO_CONTENT);
    }
}
