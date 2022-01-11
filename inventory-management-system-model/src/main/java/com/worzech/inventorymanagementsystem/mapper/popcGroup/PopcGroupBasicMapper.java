package com.worzech.inventorymanagementsystem.mapper.popcGroup;

import com.worzech.inventorymanagementsystem.domain.PopcGroup;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupBasicDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper()
public interface PopcGroupBasicMapper {

    PopcGroupBasicDto toPopcGroupBasicDto(PopcGroup popcGroup);

    PopcGroup fromPopcGroupDto(PopcGroupBasicDto popcGroupDto);


}
