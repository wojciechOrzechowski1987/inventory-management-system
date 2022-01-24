package com.worzech.inventorymanagementsystem.model.popcSubgroup;


import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
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
public class PopcSubgroupNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=20, message = "Nazwa podgrupy musi się zawierać między 3 a 20 znakami.")
    private String popcSubgroupName;
    private String popcGroupName;
    private Set<PopcMaterialBasicDto> popcMaterials = new HashSet<>();

}
