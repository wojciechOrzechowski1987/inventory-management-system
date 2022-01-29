package com.worzech.inventorymanagementsystem.model.demand;

import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Proszę wybrać projekt")
    private String projectName;
    private Status demandStatus;
    @NotEmpty(message = "Zapotrzebowanie musi zawierać materiały")
    private List<@Valid DemandPopcMaterialBasicDto> demandPopcMaterials = new ArrayList<>();


}
