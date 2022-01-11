package com.worzech.inventorymanagementsystem.mapper.vendor;


import com.worzech.inventorymanagementsystem.domain.Vendor;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemBasicMapper;
import com.worzech.inventorymanagementsystem.model.vendor.VendorNewAndEditDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {ProductItemBasicMapper.class})
public interface VendorNewAndEditMapper {

    VendorNewAndEditDto toVendorNewAndEditDto(Vendor vendor);

    Vendor fromVendorNewAndEditDto(VendorNewAndEditDto vendorDto);


}
