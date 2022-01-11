package com.worzech.inventorymanagementsystem.controller;


import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupBasicDto;
import com.worzech.inventorymanagementsystem.model.popcSubgroup.PopcSubgroupNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.popcSubgroup.PopcSubgroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "popcSubgroup", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PopcSubgroupController {

    private final PopcSubgroupService popcSubgroupService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcSubgroupNewAndEditDto> getAllPopcSubgroups() {
        return popcSubgroupService.getAllPopcSubgroups();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcSubgroupBasicDto> getAllPopcSubgroupsBasic() {
        return popcSubgroupService.getAllPopcSubgroupsBasic();
    }

    @GetMapping(path = "/notAssigned")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcSubgroupBasicDto> getNotAssignedSubgroups() {
        return popcSubgroupService.getNotAssignedSubgroups();
    }

    @GetMapping("/{id}/subgroupsForEdit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PopcSubgroupBasicDto> getPopcSubgroupsForPopcGroupEdit(@PathVariable Long id) {
        return popcSubgroupService.getPopcSubgroupsForPopcGroupEdit(id);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public PopcSubgroupNewAndEditDto findPopcSubgroupById(@PathVariable Long id) {
        return popcSubgroupService.findPopcSubgroupById(id);
    }

    @GetMapping("/basic/{popcSubgroupName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public PopcSubgroupBasicDto findPopcSubgroupByName(@PathVariable String popcSubgroupName) {
        return popcSubgroupService.findPopcSubgroupsByName(popcSubgroupName);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePopcSubgroup(@PathVariable("id") Long id) {
        popcSubgroupService.deletePopcSubgroupsById(id);
    }

    @PostMapping(path = "/newSubgroup", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PopcSubgroupNewAndEditDto createNewPopcSubroup(@RequestBody PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto) {
        return popcSubgroupService.createNewPopcSubgroup(popcSubgroupNewAndEditDto);
    }

    @PutMapping(path = "/editSubgroup/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PopcSubgroupNewAndEditDto editPopcSubgroup(@PathVariable Long id, @RequestBody PopcSubgroupNewAndEditDto popcSubgroupNewAndEditDto) {
        return popcSubgroupService.updatePopcSubgroup(id, popcSubgroupNewAndEditDto);
    }

}
