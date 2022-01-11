package com.worzech.inventorymanagementsystem.model.popcMaterial;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PopcMaterialNewAndEditDto {

    private Long id;
    private String popcMaterialCode;
    private String popcMaterialName;
    private String popcMaterialDescription;
    private String popcSubgroupName;
    private Set<ProductItemBasicDto> productItems = new HashSet<>();

}
