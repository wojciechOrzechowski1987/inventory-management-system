package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.demandPopcMaterial.DemandPopcMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "demandPopcMaterial", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DemandPopcMaterialController {

    private final DemandPopcMaterialService demandPopcMaterialService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('demand:read')")
    public List<DemandPopcMaterialNewAndEditDto> getAllDemandPopcMaterials() {
        return demandPopcMaterialService.getAllDemandPopcMaterials();
    }

    @PostMapping(path = "newDemandPopcMaterial", consumes = "application/json")
    @PreAuthorize("hasAuthority('demand:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(@RequestBody DemandPopcMaterialNewAndEditDto demandPopcMaterialNewAndEditDto) {
        return demandPopcMaterialService.createNewDemandPopcMaterial(demandPopcMaterialNewAndEditDto);
    }

   /* @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DemandPopcMaterialNewAndEditDto getDemandPopcMaterialById(@PathVariable Long id) {
        return demandPopcMaterialService.getDemandPopcMaterialById(id);
    }

    @PostMapping(path= "newDemandPopcMaterial", consumes ="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(@RequestBody DemandPopcMaterialNewAndEditDto demandPopcMaterialDto) {
        return demandPopcMaterialService.createNewDemand(demandPopcMaterialDto);
    }*/

}
