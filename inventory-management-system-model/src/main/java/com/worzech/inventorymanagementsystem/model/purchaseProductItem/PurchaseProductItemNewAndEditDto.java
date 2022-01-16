package com.worzech.inventorymanagementsystem.model.purchaseProductItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseProductItemNewAndEditDto {

    private Long id;
    private Integer quantity;
    private String productItem;
    private String popcMaterialCode;
    private String vendor;
    private String purchase;

}
