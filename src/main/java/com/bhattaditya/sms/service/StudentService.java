package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.repository.EnrollmentRepository;
import com.bhattaditya.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseService courseService;

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

        Course course = courseService.getCourse(courseId);

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("Email already exists...try another");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        studentRepository.save(student);
        enrollmentRepository.save(enrollment);

        return enrollment;
    }

    public Enrollment updateCourse(long enrollId, long courseId, long studentId) {

        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollId);

        if (enrollment.isEmpty()) {
            throw new IllegalStateException("No enrollment id exists");
        }

        Enrollment obj = enrollment.get();
        Student student = getStudent(studentId);
        Course course =  courseService.getCourse(courseId);
        String newCourseName = course.getCourseName();

        // check is already exists
        if (obj.getCourse().getId().equals(course.getId())) {
            throw new IllegalStateException("Course already assigned");
        }

        obj.setCourse(course);
        enrollmentRepository.save(obj);

        System.out.println(obj);
        return obj;

    }
}
