package com.bhattaditya.sms.repository;

import com.bhattaditya.sms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
