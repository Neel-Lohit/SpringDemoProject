package com.example.springdemo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "investors_projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestorProjects {


    @EmbeddedId
    private ProjectPK key;

    @MapsId("projectId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Projects projects;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "investor_id")
    private Investor investor;



}
