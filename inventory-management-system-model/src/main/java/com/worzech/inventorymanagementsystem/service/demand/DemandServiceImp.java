package com.worzech.inventorymanagementsystem.service.demand;

import com.worzech.inventorymanagementsystem.domain.Demand;
import com.worzech.inventorymanagementsystem.domain.DemandPopcMaterial;
import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.demand.DemandNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.demand.DemandNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.demandPopcMaterial.DemandPopcMaterialBasicDto;
import com.worzech.inventorymanagementsystem.repository.DemandPopcMaterialRepository;
import com.worzech.inventorymanagementsystem.repository.DemandRepository;
import com.worzech.inventorymanagementsystem.repository.PopcMaterialRepository;
import com.worzech.inventorymanagementsystem.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DemandServiceImp implements DemandService {

    private final DemandNewAndEditMapper demandNewAndEditMapper;
    private final DemandRepository demandRepository;
    private final PopcMaterialRepository popcMaterialRepository;
    private final DemandPopcMaterialRepository demandPopcMaterialRepository;
    private final ProjectRepository projectRepository;


    @Override
    public List<DemandNewAndEditDto> getAllDemands() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch((a -> a.getAuthority().equals("ROLE_ADMIN")))) {
            return demandRepository
                    .findAll()
                    .stream()
                    .map(demandNewAndEditMapper::toDemandNewAndEditDto)
                    .collect(Collectors.toList());
        } else {
            return demandRepository
                    .findDemandForCoordinators()
                    .stream()
                    .map(demandNewAndEditMapper::toDemandNewAndEditDto)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public DemandNewAndEditDto getDemandById(Long id) {
        return demandRepository.findById(id).map(demandNewAndEditMapper::toDemandNewAndEditDto).orElseThrow(ResourceNotFoundException::new);
    }


    @Override
    public void deleteDemandById(Long id) {
        Project project = demandRepository.findById(id).get().getProject();
        demandRepository.deleteById(id);
        project.projectStatusControl();
        projectRepository.save(project);
    }

    @Override
    public DemandNewAndEditDto createNewDemand(DemandNewAndEditDto demandNewAndEditDto) {
        return saveAndReturnDto(demandNewAndEditMapper.fromDemandNewAndEditDto(demandNewAndEditDto));
    }

    private DemandNewAndEditDto saveAndReturnDto(Demand demand) {
        Demand newDemand = new Demand();
        Project project = projectRepository.findProjectByProjectName(demand.getProject().getProjectName()).orElseThrow(ResourceNotFoundException::new);
        newDemand.setDemandName(project.getProjectCode() + "_" + String.valueOf((char) ('A' + project.getDemands().size())));
        demand.getDemandPopcMaterials().forEach(demandPopcMaterial -> newDemand.addDemandPopcMaterial(
                new DemandPopcMaterial(
                        demandPopcMaterial.getQuantity(),
                        popcMaterialRepository.findPopcMaterialByPopcMaterialCode(demandPopcMaterial.getPopcMaterial().getPopcMaterialCode())
                                .orElseThrow(ResourceNotFoundException::new))));

        newDemand.setProject(project);
        newDemand.setDemandStatus(Status.DEMAND);
        project.addDemandToProject(newDemand);
        project.projectStatusControl();

        demandRepository.save(newDemand);

        return demandNewAndEditMapper.toDemandNewAndEditDto(newDemand);
    }

    @Override
    public DemandNewAndEditDto updateDemand(Long id, DemandNewAndEditDto demandNewAndEditDto) {
        return updateAndReturnDto(id, demandNewAndEditDto);
    }

    private DemandNewAndEditDto updateAndReturnDto(Long id, DemandNewAndEditDto demandNewAndEditDto) {

        Demand demand = demandRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (!demand.getProject().getProjectName().equals(demandNewAndEditDto.getProjectName())) {
            Project oldProject = demand.getProject();
            removeDemandFromProject(oldProject, oldProject.getDemands(), demand);
            Project newProject = projectRepository.getProjectByProjectName(demandNewAndEditDto.getProjectName());
            demand.setProject(newProject);
            demand.setDemandName(newProject.getProjectCode() + "_" + String.valueOf((char) ('A' + newProject.getDemands().size())));
            newProject.addDemandToProject(demand);
        }

        iterateDemandPopcMaterials(demand, demand.getDemandPopcMaterials(), demandNewAndEditDto.getDemandPopcMaterials());

        demandNewAndEditDto.getDemandPopcMaterials().forEach(demandPopcMaterial -> demand.addDemandPopcMaterial(
                new DemandPopcMaterial(
                        demandPopcMaterial.getQuantity(),
                        popcMaterialRepository.findPopcMaterialByPopcMaterialCode(demandPopcMaterial.getPopcMaterialCode()).orElseThrow(ResourceNotFoundException::new))));

        demandRepository.save(demand);

        return demandNewAndEditMapper.toDemandNewAndEditDto(demand);
    }


    private void removeDemandFromProject(Project project, Set<Demand> set, Demand demand) {
        for (Demand element : set) {
            if (element.equals(demand)) {
                project.removeDemandFromProject(element);
                demandRepository.save(element);
            }
        }
    }

    private void iterateDemandPopcMaterials(Demand demand, List<DemandPopcMaterial> oldList, List<DemandPopcMaterialBasicDto> newList) {
        boolean update;
        boolean exists;
        for (Iterator<DemandPopcMaterial> i = oldList.iterator(); i.hasNext(); ) {
            exists = false;
            DemandPopcMaterial oldDemandPopcMaterial = i.next();
            if (newList.size() > 0) {
                for (Iterator<DemandPopcMaterialBasicDto> j = newList.iterator(); j.hasNext(); ) {
                    DemandPopcMaterialBasicDto newDemandPopcMaterial = j.next();
                    update = ifExists(oldDemandPopcMaterial, newDemandPopcMaterial);
                    if (update) {
                        j.remove();
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    demand.removeDemandPopcMaterial(oldDemandPopcMaterial);
                    i.remove();
                    demandPopcMaterialRepository.deleteById(oldDemandPopcMaterial.getId());
                }
            } else {
                demand.removeDemandPopcMaterial(oldDemandPopcMaterial);
                i.remove();
                demandPopcMaterialRepository.deleteById(oldDemandPopcMaterial.getId());
            }
        }
    }

    private boolean ifExists(DemandPopcMaterial oldDemandPopcMaterial, DemandPopcMaterialBasicDto newDemandPopcMaterial) {
        if (Objects.equals(newDemandPopcMaterial.getId(), oldDemandPopcMaterial.getId())) {
            oldDemandPopcMaterial.setQuantity(newDemandPopcMaterial.getQuantity());
            oldDemandPopcMaterial.setPopcMaterial(popcMaterialRepository.findPopcMaterialByPopcMaterialCode(newDemandPopcMaterial.getPopcMaterialCode()).orElseThrow(ResourceNotFoundException::new));
            return true;
        }
        return false;
    }

}
