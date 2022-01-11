package com.worzech.inventorymanagementsystem.model.demand;

import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DemandNewAndEditDto {

    private Long id;
    private String createDate;
    private String demandName;
    private String projectCode;
    private String projectName;
    private Status demandStatus;
    private List<DemandPopcMaterialBasicDto> demandPopcMaterials = new ArrayList<>();


}
