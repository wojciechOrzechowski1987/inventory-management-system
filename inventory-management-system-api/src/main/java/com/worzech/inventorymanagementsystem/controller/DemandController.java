package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.demand.DemandNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.demand.DemandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "demand", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DemandController {

    private final DemandService demandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('demand:read')")
    public List<DemandNewAndEditDto> getAllDemands() {
        return demandService.getAllDemands();
    }

    @GetMapping("id/{id}")
    @PreAuthorize("hasAuthority('demand:read')")
    @ResponseStatus(HttpStatus.OK)
    public DemandNewAndEditDto getDemandById(@PathVariable Long id) {
        return demandService.getDemandById(id);
    }

    @PostMapping(path = "newDemand", consumes = "application/json")
    @PreAuthorize("hasAuthority('demand:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public DemandNewAndEditDto createNewDemand(@Valid @RequestBody DemandNewAndEditDto demandNewAndEditDto) {
        return demandService.createNewDemand(demandNewAndEditDto);
    }

    @PutMapping(path = "/editDemand/{id}", consumes = "application/json")
    @PreAuthorize("hasAuthority('demand:update')")
    public DemandNewAndEditDto editDemand(@PathVariable Long id, @Valid @RequestBody DemandNewAndEditDto demandNewAndEditDto) {
        return demandService.updateDemand(id, demandNewAndEditDto);
    }

    @DeleteMapping("/delete/{demandId}")
    @PreAuthorize("hasAuthority('demand:delete')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDemand(@PathVariable("demandId") Long demandId) {
        demandService.deleteDemandById(demandId);
    }

}
