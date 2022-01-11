package com.worzech.inventorymanagementsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String productItemName;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    private PopcMaterial popcMaterial;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;
    @OneToMany(mappedBy = "productItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseProductItem> purchaseProductItem;

    public ProductItem(String productItemName, Double price, PopcMaterial popcMaterial) {
        this.productItemName = productItemName;
        this.price = price;
        this.popcMaterial = popcMaterial;
    }

    public ProductItem(String productItemName, Double price, PopcMaterial popcMaterial, Vendor vendor) {
        this.productItemName = productItemName;
        this.price = price;
        this.popcMaterial = popcMaterial;
        this.vendor = vendor;
    }

    public ProductItem(String productItemName, Double price) {
        this.productItemName = productItemName;
        this.price = price;
    }


}
