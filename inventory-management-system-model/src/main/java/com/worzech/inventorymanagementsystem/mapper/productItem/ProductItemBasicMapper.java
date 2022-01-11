package com.worzech.inventorymanagementsystem.mapper.productItem;

import com.worzech.inventorymanagementsystem.domain.ProductItem;
import com.worzech.inventorymanagementsystem.mapper.vendor.VendorBasicMapper;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemBasicDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {VendorBasicMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProductItemBasicMapper {

    @Mapping(target = "vendorName", source = "vendor.vendorName")
    ProductItemBasicDto toProductItemBasicDto(ProductItem productitem);

    @InheritInverseConfiguration
    ProductItem fromProductItemBasicDto(ProductItemBasicDto productItemDto);


}
