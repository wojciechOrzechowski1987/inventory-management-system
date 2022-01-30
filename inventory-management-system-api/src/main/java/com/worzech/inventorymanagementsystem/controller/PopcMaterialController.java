package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.model.popcMaterial.PopcMaterialNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.popcMaterial.PopcMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "popcMaterial", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PopcMaterialController {

    private final PopcMaterialService popcMaterialService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcMaterialNewAndEditDto> getAllPopcMaterials() {
        return popcMaterialService.getAllPopcMaterials();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('popcMaterial:read')")
    public List<PopcMaterialBasicDto> getAllPopcMaterialsBasic() {
        return popcMaterialService.getAllPopcMaterialsBasic();
    }

    @GetMapping("/{id}/materialsForEdit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcMaterialBasicDto> getPopcMaterialsForEdit(@PathVariable Long id) {
        return popcMaterialService.getPopcMaterialsForPopcSubgroupEdit(id);
    }

    @GetMapping(path = "/notAssigned")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcMaterialNewAndEditDto> getNotAssignedMaterials() {
        return popcMaterialService.getNotAssignedPopcMaterials();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcMaterialNewAndEditDto findPopcMaterialById(@PathVariable Long id) {
        return popcMaterialService.findPopcMaterialsById(id);
    }

    @DeleteMapping("/delete/{popcMaterialId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePopcMaterial(@PathVariable("popcMaterialId") Long popcMaterialId) {
        popcMaterialService.deletePopcMaterialsById(popcMaterialId);
    }

    @PostMapping(path = "/newMaterial", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcMaterialNewAndEditDto createNewPopcMaterial(@Valid @RequestBody PopcMaterialNewAndEditDto popcMaterialNewAndEditDto) {
        return popcMaterialService.createNewPopcMaterial(popcMaterialNewAndEditDto);
    }

    @PutMapping(path = "/editMaterial/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcMaterialNewAndEditDto editDistrict(@PathVariable Long id, @Valid @RequestBody PopcMaterialNewAndEditDto popcMaterialNewAndEditDto) {
        return popcMaterialService.updatePopcMaterial(id, popcMaterialNewAndEditDto);
    }
}
