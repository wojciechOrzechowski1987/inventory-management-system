package com.worzech.inventorymanagementsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PurchaseProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductItem productItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Purchase purchase;

    public PurchaseProductItem(Integer quantity, ProductItem productItem, Purchase purchase) {
        this.quantity = quantity;
        this.productItem = productItem;
        this.purchase = purchase;
    }

    public PurchaseProductItem(Integer quantity, ProductItem productItem) {
        this.quantity = quantity;
        this.productItem = productItem;
    }

}
