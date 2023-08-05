package com.bhattaditya.sms.repository;

import com.bhattaditya.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
