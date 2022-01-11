package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.purchaseProductItem.PurchaseProductItemNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.purchaseProductItem.PurchaseProductItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "purchaseProductItem", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PurchaseProductItemlController {

    private final PurchaseProductItemService purchaseProductItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PurchaseProductItemNewAndEditDto> getAllPurchaseProductItems() {
        return purchaseProductItemService.getAllPurchaseProductItems();
    }

   /* @PostMapping(path= "newDemandPopcMaterial", consumes ="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(@RequestBody DemandPopcMaterialNewAndEditDto demandPopcMaterialDto) {
        return demandPopcMaterialService.createNewDemandPopcMaterial(demandPopcMaterialDto);
    }*/

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
