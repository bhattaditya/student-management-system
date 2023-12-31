package com.bhattaditya.sms.repository;

import com.bhattaditya.sms.entity.Enrollment;
import com.bhattaditya.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourse(Long id);

    Student findByStudent(Student student);
}
