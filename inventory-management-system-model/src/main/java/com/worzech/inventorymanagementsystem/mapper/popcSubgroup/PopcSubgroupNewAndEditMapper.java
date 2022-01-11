package com.worzech.inventorymanagementsystem.mapper.popcSubgroup;


import com.worzech.inventorymanagementsystem.domain.PopcSubgroup;
import com.worzech.inventorymanagementsystem.mapper.popcMaterial.PopcMaterialNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupNewAndEditDto;
import org.mapstruct.*;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {PopcMaterialNewAndEditMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface PopcSubgroupNewAndEditMapper {

    @Mapping(target = "popcGroupName", source = "popcGroup.popcGroupName")
    PopcSubgroupNewAndEditDto popcSubgroupToPopcSubgroupDto(PopcSubgroup subgroup);

    @InheritInverseConfiguration
    PopcSubgroup popcSubgroupDtoToPopcSubgroup(PopcSubgroupNewAndEditDto subgroupDto);

}
