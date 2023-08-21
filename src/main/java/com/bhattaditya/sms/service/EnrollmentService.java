package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public List<Enrollment> enrolledStudents() {
        return enrollmentRepository.findAll();
    }

    public void create(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> getEnrolledStudent(long enrollId) {
        return enrollmentRepository.findById(enrollId);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollmentRepository.delete(enrollment);
    }

    public void removeEnrollments(List<Enrollment> enrollmentsToDelete) {
        for (Enrollment enrollment : enrollmentsToDelete) {
            enrollmentRepository.delete(enrollment);
        }
    }
}
