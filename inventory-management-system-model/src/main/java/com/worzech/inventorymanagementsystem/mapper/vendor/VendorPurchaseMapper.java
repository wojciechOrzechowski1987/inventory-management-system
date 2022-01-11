package com.worzech.inventorymanagementsystem.mapper.vendor;


import com.worzech.inventorymanagementsystem.domain.Vendor;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemPurchaseMapper;
import com.worzech.inventorymanagementsystem.model.vendor.VendorPurchaseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {ProductItemPurchaseMapper.class})
public interface VendorPurchaseMapper {

    VendorPurchaseDto toVendorPurchaseDto(Vendor vendor);

    Vendor fromVendorPurchaseDto(VendorPurchaseDto vendorDto);


}
