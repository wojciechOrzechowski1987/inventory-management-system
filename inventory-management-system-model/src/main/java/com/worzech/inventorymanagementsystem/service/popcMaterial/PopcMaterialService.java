package com.worzech.inventorymanagementsystem.service.popcMaterial;

import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialNewAndEditDto;

import java.util.List;

public interface PopcMaterialService {

    List<PopcMaterialNewAndEditDto> getAllPopcMaterials();

    List<PopcMaterialBasicDto> getAllPopcMaterialsBasic();

    List<PopcMaterialBasicDto> getPopcMaterialsForPopcSubgroupEdit(Long id);

    List<PopcMaterialNewAndEditDto> getNotAssignedPopcMaterials();

    PopcMaterialNewAndEditDto findPopcMaterialsById(Long id);

    void deletePopcMaterialsById(Long id);

    PopcMaterialNewAndEditDto createNewPopcMaterial(PopcMaterialNewAndEditDto popcMaterialNewAndEditDto);

    PopcMaterialNewAndEditDto updatePopcMaterial(Long id, PopcMaterialNewAndEditDto popcMaterialNewAndEditDto);


}
