package com.worzech.inventorymanagementsystem.service.purchaseProductItem;

import com.worzech.inventorymanagementsystem.mapper.purchaseProductItem.PurchaseProductItemNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.purchaseProductItem.PurchaseProductItemNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.PurchaseProductItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseProductItemlServiceImp implements PurchaseProductItemService {

    private final PurchaseProductItemRepository purchaseProductItemRepository;
    private final PurchaseProductItemNewAndEditMapper purchaseProductItemNewAndEditMapper;


    @Override
    public List<PurchaseProductItemNewAndEditDto> getAllPurchaseProductItems() {
        return purchaseProductItemRepository
                .findAll()
                .stream()
                .map(purchaseProductItemNewAndEditMapper::purchaseProductItemToPurchaseProductItemDto)
                .collect(Collectors.toList());
    }

    /*@Override
    public DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(DemandPopcMaterialNewAndEditDto demandPopcMaterialDto) {
        return saveAndReturnDto(demandPopcMaterialMapper.demandPopcMaterialDtoToDemandPopcMaterial(demandPopcMaterialDto));
    }

    private DemandPopcMaterialNewAndEditDto saveAndReturnDto(DemandPopcMaterial demandPopcMaterial) {
        DemandPopcMaterial newDemandPopcMaterial = new DemandPopcMaterial();
        return demandPopcMaterialMapper.demandPopcMaterialToDemandPopcMaterialDto(newDemandPopcMaterial);
    }*/

/* @Override
    public DemandNewAndEditDto getDemandById(Long id) {
        return demandRepository.findById(id).map(demandMapper::demandToDemandDto).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public DemandNewAndEditDto createNewDemand(DemandNewAndEditDto demandDto) {
        return saveAndReturnDto(demandMapper.demandDtoToDemand(demandDto));
    }

    private DemandNewAndEditDto saveAndReturnDto(Demand demand) {
                Demand newDemand = new Demand();
                Project project = projectRepository.getProjectByProjectName(demand.getProject().getProjectName());
                newDemand.setProject(project);
                project.projectStatusControl();
                demandRepository.save(newDemand);
                return demandMapper.demandToDemandDto(newDemand);
    }*/
}
