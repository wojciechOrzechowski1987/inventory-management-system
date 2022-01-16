package com.worzech.inventorymanagementsystem.service.purchase;

import com.worzech.inventorymanagementsystem.model.purchase.PurchaseDto;

import java.util.List;

public interface PurchaseService {

    List<PurchaseDto> getAllPurchases();

    PurchaseDto createNewPurchase(PurchaseDto purchaseDto);

    PurchaseDto updatePurchase(Long id, PurchaseDto purchaseDto);

    PurchaseDto getPurchaseById(Long id);

    void deletePurchaseById(Long id);


}
