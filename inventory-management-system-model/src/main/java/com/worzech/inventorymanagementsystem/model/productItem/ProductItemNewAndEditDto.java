package com.worzech.inventorymanagementsystem.model.productItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductItemNewAndEditDto {

    private Long id;
    private String productItemName;
    private Double price;
    private String popcMaterialCode;
    private String vendorName;

}
