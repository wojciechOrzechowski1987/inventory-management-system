package com.worzech.inventorymanagementsystem.model.popcGroup;

import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
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
public class PopcGroupNewAndEditDto {

    private Long id;
    @NotBlank(message = "Pole nie może być puste.")
    @Size(min=3,max=20, message = "Nazwa grupy musi się zawierać między 3 a 20 znakami.")
    private String popcGroupName;
    private Set<PopcSubgroupBasicDto> popcSubgroup = new HashSet<>();


}
