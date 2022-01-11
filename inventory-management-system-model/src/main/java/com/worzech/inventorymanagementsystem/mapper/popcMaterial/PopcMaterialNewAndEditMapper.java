package com.worzech.inventorymanagementsystem.mapper.popcMaterial;

import com.worzech.inventorymanagementsystem.domain.PopcMaterial;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemBasicMapper;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {ProductItemBasicMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PopcMaterialNewAndEditMapper {

    @Mapping(target = "popcSubgroupName", source = "popcSubgroup.popcSubgroupName")
    PopcMaterialNewAndEditDto popcMaterialToPopcMaterialDto(PopcMaterial popcMaterial);

    @InheritInverseConfiguration
    PopcMaterial popcMaterialDtoToPopcMaterial(PopcMaterialNewAndEditDto popcMaterialNewAndEditDto);

}
