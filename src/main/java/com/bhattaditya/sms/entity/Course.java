package com.bhattaditya.sms.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    public Course(Enrollment enrollment) {
        this.id = enrollment.getCourse().getId();
        this.courseName = enrollment.getCourse().getCourseName();
    }
}
