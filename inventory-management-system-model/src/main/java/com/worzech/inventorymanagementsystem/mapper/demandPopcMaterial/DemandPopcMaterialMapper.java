package com.worzech.inventorymanagementsystem.mapper.demandPopcMaterial;


import com.worzech.inventorymanagementsystem.domain.DemandPopcMaterial;
import com.worzech.inventorymanagementsystem.mapper.demand.DemandNewAndEditMapper;
import com.worzech.inventorymanagementsystem.mapper.popcMaterial.PopcMaterialNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {PopcMaterialNewAndEditMapper.class, DemandNewAndEditMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DemandPopcMaterialMapper {

    @Mapping(target = "popcMaterial", source = "popcMaterial.popcMaterialCode")
    @Mapping(target = "demand", source = "demand.id")
    DemandPopcMaterialNewAndEditDto demandPopcMaterialToDemandPopcMaterialDto(DemandPopcMaterial demandPopcMaterial);

    @InheritInverseConfiguration
    DemandPopcMaterial demandPopcMaterialDtoToDemandPopcMaterial(DemandPopcMaterialNewAndEditDto demandPopcMaterialNewAndEditDto);


}
