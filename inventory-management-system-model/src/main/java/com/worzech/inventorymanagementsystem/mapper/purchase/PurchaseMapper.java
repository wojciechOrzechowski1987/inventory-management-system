package com.worzech.inventorymanagementsystem.mapper.purchase;


import com.worzech.inventorymanagementsystem.domain.Purchase;
import com.worzech.inventorymanagementsystem.mapper.purchaseProductItem.PurchaseProductItemNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.purchase.PurchaseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {PurchaseProductItemNewAndEditMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PurchaseMapper {

    @Mapping(target = "demand", source = "demand.demandName")
    @Mapping(target = "project", source = "demand.project.projectName")
    PurchaseDto purchaseToPurchaseDto(Purchase purchase);

    @InheritInverseConfiguration
    Purchase purchaseDtoToPurchase(PurchaseDto purchaseDto);
}
