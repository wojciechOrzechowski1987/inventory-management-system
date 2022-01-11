package com.worzech.inventorymanagementsystem.mapper.district;


import com.worzech.inventorymanagementsystem.domain.District;
import com.worzech.inventorymanagementsystem.model.district.DistrictBasicDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Service
@Mapper()
public interface DistrictBasicMapper {

    DistrictBasicDto toDistrictBasicDto(District district);

}
