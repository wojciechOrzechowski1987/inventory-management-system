package com.worzech.inventorymanagementsystem.model.project;

import com.worzech.inventorymanagementsystem.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectNewAndEditDto {

    private Long id;
    @NonNull
    private String projectCode;
    private String projectName;
    private String district;
    private Status status;

}

