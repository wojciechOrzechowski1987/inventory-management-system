package com.worzech.inventorymanagementsystem.service.purchase;

import com.worzech.inventorymanagementsystem.domain.Demand;
import com.worzech.inventorymanagementsystem.domain.Purchase;
import com.worzech.inventorymanagementsystem.domain.PurchaseProductItem;
import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.purchase.PurchaseMapper;
import com.worzech.inventorymanagementsystem.model.purchase.PurchaseDto;
import com.worzech.inventorymanagementsystem.repository.DemandRepository;
import com.worzech.inventorymanagementsystem.repository.ProductItemRepository;
import com.worzech.inventorymanagementsystem.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PurchaseServiceimpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final DemandRepository demandRepository;
    private final ProductItemRepository productItemRepository;
    private final PurchaseMapper purchaseMapper;


    @Override
    public List<PurchaseDto> getAllPurchases() {
        return purchaseRepository
                .findAll()
                .stream()
                .map(purchaseMapper::purchaseToPurchaseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDto getPurchaseById(Long id) {
        return purchaseRepository.findById(id).map(purchaseMapper::purchaseToPurchaseDto).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deletePurchaseById(Long id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public PurchaseDto createNewPurchase(PurchaseDto purchaseDto) {
        return saveAndReturnDto(purchaseMapper.purchaseDtoToPurchase(purchaseDto));
    }

    private PurchaseDto saveAndReturnDto(Purchase purchase) {

        Purchase newPurchase = new Purchase();
        Demand demand = demandRepository.getDemandByDemandName(purchase.getDemand().getDemandName());
        newPurchase.setDemand(demand);
        purchase.getProductItems().forEach(purchaseProductItem -> newPurchase.addProductItemToPurchase(
                new PurchaseProductItem(
                        purchaseProductItem.getQuantity(),
                        productItemRepository.findProductItemByProductItemName(purchaseProductItem.getProductItem().getProductItemName()).orElseThrow(ResourceNotFoundException::new))));

        demand.setDemandStatus(Status.ORDERED);
        demand.getProject().projectStatusControl();
        purchaseRepository.save(newPurchase);

        return purchaseMapper.purchaseToPurchaseDto(newPurchase);

    }
}
