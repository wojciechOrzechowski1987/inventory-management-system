package com.worzech.inventorymanagementsystem.model.project;

import com.worzech.inventorymanagementsystem.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectBasicDto {

    @NonNull
    private String projectCode;
    private String projectName;
    private Status status;
    private String district;

}

