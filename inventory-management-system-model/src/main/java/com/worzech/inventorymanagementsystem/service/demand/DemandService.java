package com.worzech.inventorymanagementsystem.service.demand;

import com.worzech.inventorymanagementsystem.model.demand.DemandNewAndEditDto;

import java.util.List;

public interface DemandService {

    List<DemandNewAndEditDto> getAllDemands();

    DemandNewAndEditDto createNewDemand(DemandNewAndEditDto demandNewAndEditDto);

    DemandNewAndEditDto getDemandById(Long id);

    void deleteDemandById(Long id);

    DemandNewAndEditDto updateDemand(Long id, DemandNewAndEditDto demandNewAndEditDto);


}
