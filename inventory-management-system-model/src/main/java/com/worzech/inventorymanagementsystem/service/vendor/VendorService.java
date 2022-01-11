package com.worzech.inventorymanagementsystem.service.vendor;

import com.worzech.inventorymanagementsystem.model.vendor.VendorBasicDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.vendor.VendorPurchaseDto;

import java.util.List;

public interface VendorService {

    List<VendorNewAndEditDto> getAllVendors();

    List<VendorBasicDto> getAllVendorsBasic();

    List<VendorPurchaseDto> getAllVendorsPurchase();

    VendorNewAndEditDto findVendorById(Long id);

    void deleteVendorById(Long id);

    VendorNewAndEditDto createNewVendor(VendorNewAndEditDto vendorDto);

    VendorNewAndEditDto updateVendor(Long id, VendorNewAndEditDto vendorDto);

}
