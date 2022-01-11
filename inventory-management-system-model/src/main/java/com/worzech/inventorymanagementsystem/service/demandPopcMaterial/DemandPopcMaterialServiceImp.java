package com.worzech.inventorymanagementsystem.service.demandPopcMaterial;

import com.worzech.inventorymanagementsystem.domain.DemandPopcMaterial;
import com.worzech.inventorymanagementsystem.mapper.demandPopcMaterial.DemandPopcMaterialBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.demandPopcMaterial.DemandPopcMaterialMapper;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.DemandPopcMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DemandPopcMaterialServiceImp implements DemandPopcMaterialService {

    private final DemandPopcMaterialMapper demandPopcMaterialMapper;
    private final DemandPopcMaterialBasicMapper demandPopcMaterialBasicMapper;
    private final DemandPopcMaterialRepository demandPopcMaterialRepository;

    @Override
    public List<DemandPopcMaterialNewAndEditDto> getAllDemandPopcMaterials() {
        return demandPopcMaterialRepository
                .findAll()
                .stream()
                .map(demandPopcMaterialMapper::demandPopcMaterialToDemandPopcMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandPopcMaterialBasicDto> getAllBasicDemandPopcMaterials() {
        return demandPopcMaterialRepository
                .findAll()
                .stream()
                .map(demandPopcMaterialBasicMapper::toDemandPopcMaterialBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public DemandPopcMaterialNewAndEditDto createNewDemandPopcMaterial(DemandPopcMaterialNewAndEditDto demandPopcMaterialNewAndEditDto) {
        return saveAndReturnDto(demandPopcMaterialMapper.demandPopcMaterialDtoToDemandPopcMaterial(demandPopcMaterialNewAndEditDto));
    }

    private DemandPopcMaterialNewAndEditDto saveAndReturnDto(DemandPopcMaterial demandPopcMaterial) {
        DemandPopcMaterial newDemandPopcMaterial = new DemandPopcMaterial();
        return demandPopcMaterialMapper.demandPopcMaterialToDemandPopcMaterialDto(newDemandPopcMaterial);
    }

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
