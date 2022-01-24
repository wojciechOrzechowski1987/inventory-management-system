package com.worzech.inventorymanagementsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PopcMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String popcMaterialCode;
    @Column(unique = true)
    private String popcMaterialName;
    private String popcMaterialDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    private PopcSubgroup popcSubgroup;
    @OneToMany(mappedBy = "popcMaterial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductItem> productItems = new HashSet<>();
    @OneToMany(mappedBy = "popcMaterial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DemandPopcMaterial> demandPopcMaterials = new HashSet<>();

    public PopcMaterial(String popcMaterialCode, String popcMaterialName, String popcMaterialDescription, PopcSubgroup popcSubgroup) {
        this.popcMaterialCode = popcMaterialCode;
        this.popcMaterialName = popcMaterialName;
        this.popcMaterialDescription = popcMaterialDescription;
        this.popcSubgroup = popcSubgroup;
    }

    public PopcMaterial(String popcMaterialCode, String popcMaterialName, String popcMaterialDescription) {
        this.popcMaterialCode = popcMaterialCode;
        this.popcMaterialName = popcMaterialName;
        this.popcMaterialDescription = popcMaterialDescription;
    }

    public void addProductItemtoPopcMaterial(ProductItem productItem) {
        productItems.add(productItem);
        productItem.setPopcMaterial(this);
    }

    public void removeProductItemFromPopcMaterialIterate(ProductItem productItem) {
        productItem.setPopcMaterial(null);
    }


}
