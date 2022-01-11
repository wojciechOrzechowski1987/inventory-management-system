package com.worzech.inventorymanagementsystem.mapper.popcMaterial;

import com.worzech.inventorymanagementsystem.domain.PopcMaterial;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PopcMaterialBasicMapper {

    PopcMaterialBasicDto toPopcMaterialBasicDto(PopcMaterial popcMaterial);

    @InheritInverseConfiguration
    PopcMaterial fromPopcMaterialBasic(PopcMaterialBasicDto popcMaterialDto);

}
