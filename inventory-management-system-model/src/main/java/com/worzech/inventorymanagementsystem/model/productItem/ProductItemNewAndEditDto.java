package com.worzech.inventorymanagementsystem.model.productItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProductItemNewAndEditDto {

    private Long id;
    @NotBlank
    @Size(min=3,max=200, message = "Nazwa produktu musi się zawierać między 3 a 20 znakami.")
    private String productItemName;
    private Double price;
    private String popcMaterialCode;
    private String vendorName;

}
