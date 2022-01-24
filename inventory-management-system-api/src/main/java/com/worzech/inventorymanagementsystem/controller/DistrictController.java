package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.model.district.DistrictBasicDto;
import com.worzech.inventorymanagementsystem.model.district.DistrictNewAndEditDto;
import com.worzech.inventorymanagementsystem.service.district.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// @PreAuthorize ( "hasRole('ROLE_'", "hasAnyRole('ROLE_'",
//                  "hasAuthority('permission'" hasAnyAuthority('permission')

@RestController
@RequestMapping(path = "district", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping()
    @PreAuthorize("hasAuthority('district:read')")
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictNewAndEditDto> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping(path = "basic")
    @PreAuthorize("hasAuthority('district:read')")
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictBasicDto> getAllDistrictsBasic() {
        return districtService.getAllDistrictsBasic();
    }


    @DeleteMapping("/delete/{districtId}")
    @PreAuthorize("hasAuthority('district:delete')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDistrict(@PathVariable("districtId") Long districtId) {
        districtService.deleteDistrictById(districtId);
    }

    @PostMapping(path = "/newDistrict", consumes = "application/json")
    @PreAuthorize("hasAuthority('district:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public DistrictNewAndEditDto createNewDistrict(@Valid @RequestBody DistrictNewAndEditDto districtNewAndEditDto) {
        return districtService.createNewDistrict(districtNewAndEditDto);
    }

    @PutMapping(path = "/editDistrict/{id}", consumes = "application/json")
    @PreAuthorize("hasAuthority('district:update')")
    public DistrictNewAndEditDto editDistrict(@PathVariable Long id,@Valid @RequestBody DistrictNewAndEditDto districtNewAndEditDto) {
        return districtService.updateDistrict(id, districtNewAndEditDto);
    }
}
