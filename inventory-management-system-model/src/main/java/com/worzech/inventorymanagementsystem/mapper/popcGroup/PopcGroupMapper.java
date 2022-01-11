package com.worzech.inventorymanagementsystem.mapper.popcGroup;

import com.worzech.inventorymanagementsystem.domain.PopcGroup;
import com.worzech.inventorymanagementsystem.mapper.popcSubgroup.PopcSubgroupBasicMapper;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupNewAndEditDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {PopcSubgroupBasicMapper.class})
public interface PopcGroupMapper {

    PopcGroupNewAndEditDto toPopcGroupNewAndEditDto(PopcGroup popcGroup);

    PopcGroup fromPopcGroupNewAndEditDto(PopcGroupNewAndEditDto popcGroupNewAndEditDto);


}
