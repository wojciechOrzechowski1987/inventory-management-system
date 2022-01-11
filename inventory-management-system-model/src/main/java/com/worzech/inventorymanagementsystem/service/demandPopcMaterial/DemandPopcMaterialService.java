package com.worzech.inventorymanagementsystem.service.demandPopcMaterial;

import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialNewAndEditDto;

import java.util.List;

public interface DemandPopcMaterialService {

    List<DemandPopcMaterialNewAndEditDto> getAllDemandPopcMaterials();

    List<DemandPopcMaterialBasicDto> getAllBasicDemandPopcMaterials();

    DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(DemandPopcMaterialNewAndEditDto demandPopcMaterialNewAndEditDto);

   /* DemandNewAndEditDto createNewDemand(DemandNewAndEditDto demandDto);

    DemandNewAndEditDto getDemandById(Long id);*/

    /*DistrictNewAndEditDto getDistrictByName(String districtName);

    DistrictNewAndEditDto getDistrictById(Long id);

    void deleteDistrictById(Long id);

    DistrictNewAndEditDto createNewDistrict(DistrictNewAndEditDto districtDto);

    DistrictNewAndEditDto updateDistrict(Long id, DistrictNewAndEditDto districtDto);*/

}
