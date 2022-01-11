package com.worzech.inventorymanagementsystem.mapper.demand;

import com.worzech.inventorymanagementsystem.domain.Demand;
import com.worzech.inventorymanagementsystem.mapper.demandPopcMaterial.DemandPopcMaterialBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.project.ProjectBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.purchase.PurchaseMapper;
import com.worzech.inventorymanagementsystem.model.demand.DemandNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {ProjectBasicMapper.class, DemandPopcMaterialBasicMapper.class, PurchaseMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DemandNewAndEditMapper {

    @Mapping(target = "projectName", source = "project.projectName")
    @Mapping(target = "projectCode", source = "project.projectCode")
    DemandNewAndEditDto toDemandNewAndEditDto(Demand demand);

    @InheritInverseConfiguration
    Demand fromDemandNewAndEditDto(DemandNewAndEditDto demandNewAndEditDto);
}
