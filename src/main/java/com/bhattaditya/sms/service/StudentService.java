package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.repository.CourseRepository;
import com.bhattaditya.sms.repository.EnrollmentRepository;
import com.bhattaditya.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student Not found"));
    }

    public List<Enrollment> enrolledStudents(String subject) {
        List<Enrollment> enrolledStudents = enrollmentRepository.findAll();

        List<Enrollment> filteredEnrolledStudents =  enrolledStudents.stream()
                .filter(sub -> sub.getCourse().getCourseName().equalsIgnoreCase(subject))
                .toList();

        if (filteredEnrolledStudents.isEmpty()) {
            throw new IllegalStateException("No enrolled student or subject ");
        }

        return filteredEnrolledStudents;
    }

    public List<Course> studentCourses(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Student with " + email  + " not found"));
        List<Enrollment> enrolledStudents = enrollmentRepository.findAll();

        List<Enrollment> filteredList = enrolledStudents.stream()
                .filter(obj -> obj.getStudent().getEmail().equalsIgnoreCase(email))
                .toList();

        return filteredList.stream()
                .map(Course::new)
                .toList();

    }

    public Enrollment enrollStudent(long courseId, Student student) {

        Course course = courseRepository.findById(courseId).orElseThrow(()-> new IllegalStateException("Course not found"));

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("Student already exists...");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        studentRepository.save(student);
        enrollmentRepository.save(enrollment);

        return enrollment;
    }

}
