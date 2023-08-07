package com.bhattaditya.sms.controller;

import com.bhattaditya.sms.entity.Course;
import com.bhattaditya.sms.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TEST course controller

@RestController
@RequestMapping("/courses")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourses() {
        LOGGER.info("Getting courses...");

        return courseService.getCourses();
    }

    @GetMapping(value = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getCourse(@PathVariable("courseId") long courseId) {
        LOGGER.info("Getting course: " + courseId);

        return courseService.getCourse(courseId);
    }
}
