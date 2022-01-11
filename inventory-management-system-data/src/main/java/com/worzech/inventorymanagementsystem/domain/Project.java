package com.worzech.inventorymanagementsystem.domain;

import com.worzech.inventorymanagementsystem.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String projectCode;
    @NotBlank
    @Column(unique = true)
    private String projectName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Demand> demands = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private District district;


    public Project(String projectCode, String projectName, District district) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.status = Status.NEW;
        this.district = district;
    }

    public Project(String projectCode, String projectName) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.status = Status.NEW;
    }

    public Project(String projectCode, String projectName, District district, Status status) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.status = status;
        this.district = district;
    }

    public void addDemandToProject(Demand demand) {
        demands.add(demand);
        demand.setProject(this);
        projectStatusControl();
    }

    public void removeDemandFromProject(Demand demand) {
        demand.setProject(null);
        demands.remove(demand);
        projectStatusControl();
    }

    public void projectStatusControl() {
        if (demands.isEmpty()) {
            this.setStatus(Status.NEW);
        } else {
            this.setStatus(Status.ORDERED);
            for (Demand demand : demands) {
                if (demand.getDemandStatus().equals(Status.DEMAND)) {
                    this.setStatus(Status.DEMAND);
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!projectCode.equals(project.projectCode)) return false;
        return projectName.equals(project.projectName);
    }

    @Override
    public int hashCode() {
        int result = projectCode.hashCode();
        result = 31 * result + projectName.hashCode();
        return result;
    }
}
