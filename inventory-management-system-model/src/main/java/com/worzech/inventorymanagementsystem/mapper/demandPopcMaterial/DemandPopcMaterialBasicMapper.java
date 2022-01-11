package com.worzech.inventorymanagementsystem.mapper.demandPopcMaterial;


import com.worzech.inventorymanagementsystem.domain.DemandPopcMaterial;
import com.worzech.inventorymanagementsystem.mapper.popcMaterial.PopcMaterialBasicMapper;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {PopcMaterialBasicMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DemandPopcMaterialBasicMapper {

    @Mapping(target = "popcMaterialCode", source = "popcMaterial.popcMaterialCode")
    DemandPopcMaterialBasicDto toDemandPopcMaterialBasicDto(DemandPopcMaterial demandPopcMaterial);

    @InheritInverseConfiguration
    DemandPopcMaterial fromDemandPopcMaterialBasicDto(DemandPopcMaterialBasicDto demandPopcMaterialBasicDto);


}
