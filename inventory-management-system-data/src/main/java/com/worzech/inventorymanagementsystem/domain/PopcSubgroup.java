package com.worzech.inventorymanagementsystem.domain;

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
public class PopcSubgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String popcSubgroupName;
    @ManyToOne(fetch = FetchType.LAZY)
    private PopcGroup popcGroup;
    @OneToMany(mappedBy = "popcSubgroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PopcMaterial> popcMaterials = new HashSet<>();


    public PopcSubgroup(String popcSubgroupName) {
        this.popcSubgroupName = popcSubgroupName;
    }

    public PopcSubgroup(String popcSubgroupName, PopcGroup popcGroup) {
        this.popcSubgroupName = popcSubgroupName;
        this.popcGroup = popcGroup;
    }

    public void addPopcMaterialToPopcSubgroup(PopcMaterial material) {
        popcMaterials.add(material);
        material.setPopcSubgroup(this);
    }

    public void removePopcMaterialFromPopcSubgroup(PopcMaterial material) {
        material.setPopcSubgroup(null);
    }

}
