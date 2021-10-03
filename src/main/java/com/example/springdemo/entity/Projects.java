package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Projects {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "investor_id")
    private int investorId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "techstack")
    private String techstack;

    @Column(name = "investment")
    private int investment;

    @Column(name = "duration")
    private String duration;





}
