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
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String vendorName;
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductItem> productItems = new HashSet<>();

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public void addProductItemToVendor(ProductItem productItem) {
        productItems.add(productItem);
        productItem.setVendor(this);
    }

    public void removeProductItemFromVendorIterate(ProductItem productItem) {
        productItem.setVendor(null);
    }

}
