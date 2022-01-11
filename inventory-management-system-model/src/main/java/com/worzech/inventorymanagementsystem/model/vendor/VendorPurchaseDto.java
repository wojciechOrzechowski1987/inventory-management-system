package com.worzech.inventorymanagementsystem.model.vendor;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemPurchaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VendorPurchaseDto {

    private Long id;
    private String vendorName;
    private Set<ProductItemPurchaseDto> productItems = new HashSet<>();

}
