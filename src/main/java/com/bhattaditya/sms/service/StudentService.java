package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.repository.CourseRepository;
import com.bhattaditya.sms.repository.EnrollmentRepository;
import com.bhattaditya.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
