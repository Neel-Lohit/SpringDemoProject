package com.example.springdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "gender")
    private String gender;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "organisation")
    private String organisation;

    @Column(name = "city")
    private String city;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(int id, String firstName, String lastName, String email, String phoneNo, String gender, String qualification, String organisation, String city, String state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.qualification = qualification;
        this.organisation = organisation;
        this.city = city;
        this.state = state;
    }

    public User(int id, String userName, String password, String firstName, String lastName, String email, String phoneNo, String gender, String qualification, String organisation, String city, String state, Collection<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.qualification = qualification;
        this.organisation = organisation;
        this.city = city;
        this.roles = roles;
        this.state = state;
    }

    @Column(name = "state")
    private String state;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "project", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Projects> userProjects;

    public void addProject(Projects project)
    {
        if (userProjects == null)
        {
            userProjects = new ArrayList<>();
        }
        userProjects.add(project);
    }



}
