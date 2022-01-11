package com.worzech.inventorymanagementsystem.mapper.district;


import com.worzech.inventorymanagementsystem.domain.District;
import com.worzech.inventorymanagementsystem.mapper.project.ProjectBasicMapper;
import com.worzech.inventorymanagementsystem.model.district.DistrictNewAndEditDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Service
@Mapper(uses = {ProjectBasicMapper.class})
public interface DistrictNewAndEditMapper {

    DistrictNewAndEditDto toDistrictDto(District district);

    District fromDistrictDto(DistrictNewAndEditDto districtNewAndEditDto);


}
