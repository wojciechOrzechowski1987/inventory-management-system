package com.worzech.inventorymanagementsystem.domain;

import com.worzech.inventorymanagementsystem.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    /*@NotBlank*/
    private String demandName;

    private Date createDate = new Date();

    private Status demandStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DemandPopcMaterial> demandPopcMaterials = new ArrayList<>();

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();


    public Demand(String demandName, Project project, Status demandStatus) {
        this.demandName = demandName;
        this.project = project;
        this.demandStatus = demandStatus;
    }

    public Demand(Project project) {
        this.project = project;
        this.demandName = project.getProjectName();
    }

    public void addDemandPopcMaterial(DemandPopcMaterial demandPopcMaterial) {
        demandPopcMaterials.add(demandPopcMaterial);
        demandPopcMaterial.setDemand(this);
    }

    public void removeDemandPopcMaterial(DemandPopcMaterial demandPopcMaterial) {
        demandPopcMaterial.setDemand(null);
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setDemand(this);
    }

    public void removePurchase(Purchase purchase) {
        purchase.setDemand(null);
    }

}
