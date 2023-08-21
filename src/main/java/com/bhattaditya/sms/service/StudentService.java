package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import com.bhattaditya.sms.exception.ResourceNotFound;
import com.bhattaditya.sms.exception.SMSRuntimeException;
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
    EnrollmentService enrollmentService;

    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();

//        List<StudentDTO> studentDTOS = students.stream().map(student -> {
//            StudentDTO studentDTO = new StudentDTO();
//            studentDTO.setId(student.getId());
//            studentDTO.setCity(student.getCity());
//
//            return  studentDTO;
//
//        }).toList();

        return students;
    }

    public Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFound("Student", "Id", studentId));
    }

    public List<Enrollment> enrolledStudents(long courseId) {
        String subject = courseService.getCourse(courseId).getCourseName();
        List<Enrollment> enrolledStudents = enrollmentService.enrolledStudents();

        List<Enrollment> filteredEnrolledStudents =  enrolledStudents.stream()
                .filter(sub -> sub.getCourse().getCourseName().equalsIgnoreCase(subject))
                .toList();

        if (filteredEnrolledStudents.isEmpty()) {
            throw new SMSRuntimeException("No enrolled student");
        }

        return filteredEnrolledStudents;
    }

    public List<Course> studentCourses(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("Student ", "email", email));
        List<Enrollment> enrolledStudents = enrollmentService.enrolledStudents();

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
            throw new SMSRuntimeException("Email already exists..try another");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        studentRepository.save(student);
        enrollmentService.create(enrollment);

        return enrollment;
    }

    public Enrollment updateCourse(long enrollId, long courseId, long studentId) {

        Optional<Enrollment> enrollment = enrollmentService.getEnrolledStudent(enrollId);

        if (enrollment.isEmpty()) {
            throw new SMSRuntimeException("No enrolled id exists");
        }

        Enrollment obj = enrollment.get();
        Student student = getStudent(studentId);
        Course course =  courseService.getCourse(courseId);
        String newCourseName = course.getCourseName();

        // check is already exists
        if (obj.getCourse().getId().equals(course.getId())) {
            throw new SMSRuntimeException("Course already assigned");
        }

        obj.setCourse(course);
        enrollmentService.create(obj);

        System.out.println(obj);
        return obj;

    }

    public void removeStudent(long studentId) {
        Student studentToDelete = getStudent(studentId);
        List<Enrollment> enrolledStudents = enrollmentService.enrolledStudents();

        List<Enrollment> enrollmentsToDelete = enrolledStudents.stream()
                .filter(obj -> obj.getStudent().getId().equals(studentToDelete.getId()))
                .toList();

        enrollmentService.removeEnrollments(enrollmentsToDelete);
        studentRepository.delete(studentToDelete);

        // also on delete cascade can be used
    }

    public void withdrawName(long enrollmentId) {
        Enrollment enrollment = enrollmentService.getEnrolledStudent(enrollmentId)
                .orElseThrow(()-> new SMSRuntimeException("enrollment id does not exists"));

        enrollmentService.removeEnrollment(enrollment);
    }
}
