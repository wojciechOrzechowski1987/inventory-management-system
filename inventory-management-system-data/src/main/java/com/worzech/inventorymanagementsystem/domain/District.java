package com.worzech.inventorymanagementsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String districtName;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();
    private String owner;
    private Date createDate = new Date();


    public District(String districtName, String owner) {

        this.districtName = districtName;
        this.owner = owner;
    }

    public void addProjectToDistrict(Project project) {
        projects.add(project);
        project.setDistrict(this);
    }

    public void removeProjectFromDistrictIterate(Project project) {
        project.setDistrict(null);
    }


}
