package com.example.springdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "investor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Investor{

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

    @Column(name = "state")
    private String state;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "investors_roles",
            joinColumns = @JoinColumn(name = "investor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "investor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH,CascadeType.REMOVE})
    private List<InvestorProjects> investorProjects;


    public Investor(int id, String userName, String password, String firstName, String lastName, String email, String phoneNo, String gender, String qualification, String organisation, String city, String state) {
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
        this.state = state;
    }










        




}
