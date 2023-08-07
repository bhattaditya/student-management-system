package com.bhattaditya.sms.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = true)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(nullable = false)
    private char gender;

    @Email
    @Column(unique = true, nullable = true)
    private String email = "";

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String address;

}
