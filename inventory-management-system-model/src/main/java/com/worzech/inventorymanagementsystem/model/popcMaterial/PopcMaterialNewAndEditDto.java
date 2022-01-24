package com.worzech.inventorymanagementsystem.model.popcMaterial;

import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PopcMaterialNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=20, message = "Kod POPC musi się zawierać między 3 a 20 znakami.")
    private String popcMaterialCode;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=30, message = "Nazwa materiału POPC musi się zawierać między 3 a 30 znakami.")
    private String popcMaterialName;
    private String popcMaterialDescription;
    private String popcSubgroupName;
    private Set<ProductItemBasicDto> productItems = new HashSet<>();

}
