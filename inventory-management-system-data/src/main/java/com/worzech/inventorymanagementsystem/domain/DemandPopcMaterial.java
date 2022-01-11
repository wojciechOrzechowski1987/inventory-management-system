package com.worzech.inventorymanagementsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DemandPopcMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne
    private PopcMaterial popcMaterial;
    @ManyToOne
    private Demand demand;

    public DemandPopcMaterial(Integer quantity, PopcMaterial popcMaterial, Demand demand) {
        this.quantity = quantity;
        this.popcMaterial = popcMaterial;
        this.demand = demand;
    }

    public DemandPopcMaterial(Integer quantity, PopcMaterial popcMaterial) {
        this.quantity = quantity;
        this.popcMaterial = popcMaterial;
    }


}
