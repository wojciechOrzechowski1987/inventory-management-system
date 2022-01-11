package com.worzech.inventorymanagementsystem.mapper.productItem;

import com.worzech.inventorymanagementsystem.domain.ProductItem;
import com.worzech.inventorymanagementsystem.model.productItem.ProductItemNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProductItemNewAndEditMapper {

    @Mapping(target = "popcMaterialCode", source = "popcMaterial.popcMaterialCode")
    @Mapping(target = "vendorName", source = "vendor.vendorName")
    ProductItemNewAndEditDto productItemToProductItemDto(ProductItem productitem);

    @InheritInverseConfiguration
    ProductItem productItemDtoToProductItem(ProductItemNewAndEditDto productItemNewAndEditDto);


}
