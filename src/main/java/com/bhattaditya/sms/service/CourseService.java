package com.bhattaditya.sms.service;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException("Course not found"));
    }
}
