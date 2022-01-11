package com.worzech.inventorymanagementsystem.model.district;

import com.worzech.inventorymanagementsystem.model.project.ProjectBasicDto;
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
public class DistrictNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=20, message = "Nazwa regionu musi się zawierać między 3 a 20 znakami.")
    private String districtName;
    private String owner;
    private Set<ProjectBasicDto> projects = new HashSet<>();

}

