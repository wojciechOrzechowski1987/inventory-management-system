package com.worzech.inventorymanagementsystem.model.project;

import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.model.demand.DemandNewAndEditDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProjectOrderDto {

    private Long id;
    @NonNull
    private String projectCode;
    private String projectName;
    private String district;
    private Status status;
    private Set<DemandNewAndEditDto> demands;

}

