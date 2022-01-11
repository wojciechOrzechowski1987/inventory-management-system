package com.worzech.inventorymanagementsystem.service.purchaseProductItem;

import com.worzech.inventorymanagementsystem.model.purchaseProductItem.PurchaseProductItemNewAndEditDto;

import java.util.List;

public interface PurchaseProductItemService {

    List<PurchaseProductItemNewAndEditDto> getAllPurchaseProductItems();

    //DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(DemandPopcMaterialNewAndEditDto demandPopcMaterialDto);

   /* DemandNewAndEditDto createNewDemand(DemandNewAndEditDto demandDto);

    DemandNewAndEditDto getDemandById(Long id);*/

    /*DistrictNewAndEditDto getDistrictByName(String districtName);

    DistrictNewAndEditDto getDistrictById(Long id);

    void deleteDistrictById(Long id);

    DistrictNewAndEditDto createNewDistrict(DistrictNewAndEditDto districtDto);

    DistrictNewAndEditDto updateDistrict(Long id, DistrictNewAndEditDto districtDto);*/

}
