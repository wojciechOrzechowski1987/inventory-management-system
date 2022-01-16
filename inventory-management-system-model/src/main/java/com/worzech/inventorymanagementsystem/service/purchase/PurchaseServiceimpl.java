package com.worzech.inventorymanagementsystem.service.purchase;

import com.worzech.inventorymanagementsystem.domain.Demand;
import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.domain.Purchase;
import com.worzech.inventorymanagementsystem.domain.PurchaseProductItem;
import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.purchase.PurchaseMapper;
import com.worzech.inventorymanagementsystem.model.purchase.PurchaseDto;
import com.worzech.inventorymanagementsystem.repository.DemandRepository;
import com.worzech.inventorymanagementsystem.repository.ProductItemRepository;
import com.worzech.inventorymanagementsystem.repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;
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
        Purchase purchase = purchaseRepository.findById(id).get();
        Project project = purchase.getDemand().getProject();
        Demand demand = purchase.getDemand();
        purchaseRepository.deleteById(id);
        demand.setDemandStatus(Status.DEMAND);
        demandRepository.save(demand);
        project.projectStatusControl();
        projectRepository.save(project);
    }

    @Override
    public PurchaseDto createNewPurchase(PurchaseDto purchaseDto) {
        return saveAndReturnDto(purchaseMapper.purchaseDtoToPurchase(purchaseDto));
    }

    private PurchaseDto saveAndReturnDto(Purchase purchase) {

        Purchase newPurchase = new Purchase();
        Demand demand = demandRepository.getDemandByDemandName(purchase.getDemand().getDemandName());
        newPurchase.setDemand(demand);
        newPurchase.setVendorName(purchase.getVendorName());
        purchase.getProductItems().forEach(purchaseProductItem -> newPurchase.addProductItemToPurchase(
                new PurchaseProductItem(
                        purchaseProductItem.getQuantity(),
                        productItemRepository.findProductItemByProductItemName(purchaseProductItem.getProductItem().getProductItemName()).orElseThrow(ResourceNotFoundException::new))));

        demand.setDemandStatus(Status.ORDERED);
        demand.getProject().projectStatusControl();
        purchaseRepository.save(newPurchase);

        return purchaseMapper.purchaseToPurchaseDto(newPurchase);

    }

    @Override
    public PurchaseDto updatePurchase(Long id, PurchaseDto purchaseDto) {
        return null;
    }



   /* @Override
    public PurchaseDto updatePurchase(Long id, PurchaseDto purchaseDto) {
        return updateAndReturnDto(id, purchaseDto);
    }

    private PurchaseDto updateAndReturnDto(Long id, PurchaseDto purchaseDto) {

        Purchase purchase = purchaseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
       *//* if (!demand.getProject().getProjectName().equals(demandNewAndEditDto.getProjectName())) {
            Project oldProject = demand.getProject();
            removeDemandFromProject(oldProject, oldProject.getDemands(), demand);
            Project newProject = projectRepository.getProjectByProjectName(demandNewAndEditDto.getProjectName());
            demand.setProject(newProject);
            newProject.addDemandToProject(demand);
        }

        iterateDemandPopcMaterials(demand, demand.getDemandPopcMaterials(), demandNewAndEditDto.getDemandPopcMaterials());

        demandNewAndEditDto.getDemandPopcMaterials().forEach(demandPopcMaterial -> demand.addDemandPopcMaterial(
                new DemandPopcMaterial(
                        demandPopcMaterial.getQuantity(),
                        popcMaterialRepository.findPopcMaterialByPopcMaterialCode(demandPopcMaterial.getPopcMaterialCode()).orElseThrow(ResourceNotFoundException::new))));

        demandRepository.save(demand);*//*

        return demandNewAndEditMapper.toDemandNewAndEditDto(demand);
    }*/


}
