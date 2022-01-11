package com.worzech.inventorymanagementsystem.mapper.vendor;


import com.worzech.inventorymanagementsystem.domain.Vendor;
import com.worzech.inventorymanagementsystem.mapper.productItem.ProductItemNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.vendor.VendorBasicDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {ProductItemNewAndEditMapper.class})
public interface VendorBasicMapper {

    VendorBasicDto toVendorBasicDto(Vendor vendor);

    Vendor fromVendorBasicDto(VendorBasicDto vendorDto);


}
