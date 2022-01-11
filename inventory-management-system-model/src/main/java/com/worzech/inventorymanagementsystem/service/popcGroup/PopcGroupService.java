package com.worzech.inventorymanagementsystem.service.popcGroup;

import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcGroup.PopcGroupNewAndEditDto;

import java.util.List;

public interface PopcGroupService {

    List<PopcGroupNewAndEditDto> getAllPopcGroups();

    List<PopcGroupBasicDto> getAllPopcGroupsBasic();

    PopcGroupNewAndEditDto findPopcGroupById(Long id);

    void deletePopcGroupById(Long id);

    PopcGroupNewAndEditDto createNewPopcGroup(PopcGroupNewAndEditDto popcGroupNewAndEditDto);

    PopcGroupNewAndEditDto updatePopcGroup(Long id, PopcGroupNewAndEditDto popcGroupNewAndEditDto);

}
