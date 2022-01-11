package com.worzech.inventorymanagementsystem.model.popcGroup;

import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PopcGroupNewAndEditDto {

    private Long id;
    @NonNull
    private String popcGroupName;
    private Set<PopcSubgroupBasicDto> popcSubgroup = new HashSet<>();


}
