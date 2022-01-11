package com.worzech.inventorymanagementsystem.model.productItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductItemPurchaseDto {

    private String productItemName;
    private String vendorName;
    private String popcMaterialCode;

}
