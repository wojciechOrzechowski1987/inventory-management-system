package com.worzech.inventorymanagementsystem.mapper.popcSubgroup;


import com.worzech.inventorymanagementsystem.domain.PopcSubgroup;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface PopcSubgroupBasicMapper {


    PopcSubgroupBasicDto toPopcSubgroupBasicDto(PopcSubgroup subgroup);

    @InheritInverseConfiguration
    PopcSubgroup fromPopcSubgroupDto(PopcSubgroupBasicDto subgroupDto);

}
