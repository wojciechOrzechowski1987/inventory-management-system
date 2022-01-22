package com.worzech.inventorymanagementsystem.model.project;

import com.worzech.inventorymanagementsystem.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProjectNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=4,max=4, message = "Kod projektu musi zawierać dokładnie 4 znaki.")
    private String projectCode;
    @Size(min=9,max=30, message = "Nazwa projektu musi się zawierać między 9 a 30 znakami.")
    @NotBlank(message = "Pole nie może być puste.")
    private String projectName;
    private String district;
    private Status status;

}

