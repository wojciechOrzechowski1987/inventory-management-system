package com.worzech.inventorymanagementsystem.service.popcSubgroup;

import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupNewAndEditDto;

import java.util.List;

public interface PopcSubgroupService {

    List<PopcSubgroupNewAndEditDto> getAllPopcSubgroups();

    List<PopcSubgroupBasicDto> getAllPopcSubgroupsBasic();

    List<PopcSubgroupBasicDto> getNotAssignedSubgroups();

    List<PopcSubgroupBasicDto> getPopcSubgroupsForPopcGroupEdit(Long id);

    PopcSubgroupNewAndEditDto findPopcSubgroupById(Long id);

    PopcSubgroupBasicDto findPopcSubgroupsByName(String popcSubgroupName);

    void deletePopcSubgroupsById(Long id);

    PopcSubgroupNewAndEditDto createNewPopcSubgroup(PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto);

    PopcSubgroupNewAndEditDto updatePopcSubgroup(Long id, PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto);


}
