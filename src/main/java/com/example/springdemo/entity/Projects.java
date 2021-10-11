package com.example.springdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Projects {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "techstack")
    private String techstack;

    @Column(name = "investment")
    private String investment;

    @Column(name = "duration")
    private String duration;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "projects", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<InvestorProjects> investorProjects;





    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", techstack='" + techstack + '\'' +
                ", investment='" + investment + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
