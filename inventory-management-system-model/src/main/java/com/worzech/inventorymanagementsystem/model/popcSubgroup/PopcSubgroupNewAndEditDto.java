package com.worzech.inventorymanagementsystem.model.popcSubgroup;


import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PopcSubgroupNewAndEditDto {

    private Long id;
    @NonNull
    private String popcSubgroupName;
    private String popcGroupName;
    private Set<PopcMaterialBasicDto> popcMaterials = new HashSet<>();

}
