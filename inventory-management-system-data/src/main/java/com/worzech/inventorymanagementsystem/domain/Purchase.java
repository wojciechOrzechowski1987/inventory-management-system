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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date orderDate = new Date();

    private String vendorName;

    private Status purchaseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Demand demand;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseProductItem> productItems = new ArrayList<>();

    public Purchase(Demand demand) {
        this.demand = demand;
    }

    public void addProductItemToPurchase(PurchaseProductItem productItem) {
        productItems.add(productItem);
        productItem.setPurchase(this);
    }

}
