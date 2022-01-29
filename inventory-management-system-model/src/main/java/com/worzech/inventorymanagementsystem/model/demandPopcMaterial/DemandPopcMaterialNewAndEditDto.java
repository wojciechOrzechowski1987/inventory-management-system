package com.worzech.inventorymanagementsystem.model.demandPopcMaterial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DemandPopcMaterialNewAndEditDto {

    private Long id;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String popcMaterial;
    private String demand;

}
