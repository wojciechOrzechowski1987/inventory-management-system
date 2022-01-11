package com.worzech.inventorymanagementsystem.model.demandPopcMaterial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DemandPopcMaterialNewAndEditDto {

    private Long id;
    private Integer quantity;
    private String popcMaterial;
    private String demand;

}
