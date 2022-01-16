package com.worzech.inventorymanagementsystem.model.purchase;

import com.worzech.inventorymanagementsystem.model.purchaseProductItem.PurchaseProductItemNewAndEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDto {

    private Long id;
    private String orderDate;
    private String demand;
    private String vendorName;
    private String project;
    private List<PurchaseProductItemNewAndEditDto> productItems = new ArrayList<>();


}
