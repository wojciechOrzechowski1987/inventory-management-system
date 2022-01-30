package com.worzech.inventorymanagementsystem.controller;


import com.worzech.inventorymanagementsystem.model.vendor.VendorBasicDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorPurchaseDto;
import com.worzech.inventorymanagementsystem.service.vendor.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "vendor", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<VendorNewAndEditDto> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<VendorBasicDto> getAllVendorsBasic() {
        return vendorService.getAllVendorsBasic();
    }

    @GetMapping("/purchase")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<VendorPurchaseDto> getAllVendorsPurchase() {
        return vendorService.getAllVendorsPurchase();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public VendorNewAndEditDto findVendorById(@PathVariable Long id) {
        return vendorService.findVendorById(id);
    }

    @DeleteMapping("/delete/{vendorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteVendor(@PathVariable("vendorId") Long vendorId) {
        vendorService.deleteVendorById(vendorId);
    }

    @PostMapping(path = "/newVendor", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public VendorNewAndEditDto createNewVendor(@Valid @RequestBody VendorNewAndEditDto vendorDto) {
        return vendorService.createNewVendor(vendorDto);
    }

    @PutMapping(path = "/editVendor/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public VendorNewAndEditDto updateVendor(@PathVariable Long id, @Valid @RequestBody VendorNewAndEditDto vendorDto) {
        return vendorService.updateVendor(id, vendorDto);
    }


}
