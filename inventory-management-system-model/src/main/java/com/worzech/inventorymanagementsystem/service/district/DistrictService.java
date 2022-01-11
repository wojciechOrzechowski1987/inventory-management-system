package com.worzech.inventorymanagementsystem.service.district;

import com.worzech.inventorymanagementsystem.model.district.DistrictBasicDto;
import com.worzech.inventorymanagementsystem.model.district.DistrictNewAndEditDto;

import java.util.List;

public interface DistrictService {

    List<DistrictNewAndEditDto> getAllDistricts();

    List<DistrictBasicDto> getAllDistrictsBasic();

    DistrictNewAndEditDto findDistrictById(Long id);

    DistrictBasicDto findDistrictByName(String districtName);

    void deleteDistrictById(Long id);

    DistrictNewAndEditDto createNewDistrict(DistrictNewAndEditDto districtNewAndEditDto);

    DistrictNewAndEditDto updateDistrict(Long id, DistrictNewAndEditDto districtNewAndEditDto);

}
