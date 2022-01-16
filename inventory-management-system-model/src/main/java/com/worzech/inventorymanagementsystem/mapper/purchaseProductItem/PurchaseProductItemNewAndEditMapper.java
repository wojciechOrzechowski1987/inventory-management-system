package com.worzech.inventorymanagementsystem.mapper.purchaseProductItem;


import com.worzech.inventorymanagementsystem.domain.PurchaseProductItem;
import com.worzech.inventorymanagementsystem.model.purchaseProductItem.PurchaseProductItemNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PurchaseProductItemNewAndEditMapper {

    @Mapping(target = "productItem", source = "productItem.productItemName")
    @Mapping(target = "vendor", source = "productItem.vendor.vendorName")
    @Mapping(target = "popcMaterialCode", source = "productItem.popcMaterial.popcMaterialCode")
    @Mapping(target = "purchase", source = "purchase.id")
    PurchaseProductItemNewAndEditDto purchaseProductItemToPurchaseProductItemDto(PurchaseProductItem purchaseProductItem);

    @InheritInverseConfiguration
    PurchaseProductItem purchaseProductItemDtoToPurchaseProductItem(PurchaseProductItemNewAndEditDto purchaseProductItemNewAndEditDto);


}
