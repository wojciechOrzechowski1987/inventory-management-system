package com.worzech.inventorymanagementsystem.service.district;

import com.worzech.inventorymanagementsystem.domain.District;
import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.district.DistrictBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.district.DistrictNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.district.DistrictBasicDto;
import com.worzech.inventorymanagementsystem.model.district.DistrictNewAndEditDto;
import com.worzech.inventorymanagementsystem.repository.DistrictRepository;
import com.worzech.inventorymanagementsystem.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DistrictServiceImp implements DistrictService {

    private final DistrictNewAndEditMapper districtNewAndEditMapper;
    private final DistrictBasicMapper districtBasicMapper;
    private final DistrictRepository districtRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<DistrictNewAndEditDto> getAllDistricts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch((a -> a.getAuthority().equals("ROLE_ADMIN")))) {
            return districtRepository
                    .findAll()
                    .stream()
                    .map(districtNewAndEditMapper::toDistrictDto)
                    .collect(Collectors.toList());
        } else {
            return districtRepository
                    .findDistrictByOwner()
                    .stream()
                    .map(districtNewAndEditMapper::toDistrictDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<DistrictBasicDto> getAllDistrictsBasic() {
        return districtRepository
                .findAll()
                .stream()
                .map(districtBasicMapper::toDistrictBasicDto)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictNewAndEditDto findDistrictById(Long id) {
        return districtRepository.findById(id)
                .map(districtNewAndEditMapper::toDistrictDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public DistrictBasicDto findDistrictByName(String districtName) {
        return districtBasicMapper.toDistrictBasicDto(districtRepository
                .findDistrictByDistrictName(districtName)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public void deleteDistrictById(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public DistrictNewAndEditDto createNewDistrict(DistrictNewAndEditDto districtNewAndEditDto) {
        return saveAndReturnDto(districtNewAndEditMapper.fromDistrictDto(districtNewAndEditDto));
    }

    private DistrictNewAndEditDto saveAndReturnDto(District district) {
        District newDistrict = new District();
        newDistrict.setDistrictName(district.getDistrictName());
        newDistrict.setOwner(district.getOwner());
        district.getProjects()
                .iterator()
                .forEachRemaining
                        (project ->
                                newDistrict
                                        .addProjectToDistrict(projectRepository
                                                .findProjectByProjectName(project.getProjectName())
                                                .orElseThrow(ResourceNotFoundException::new)));

        districtRepository.save(newDistrict);

        return districtNewAndEditMapper.toDistrictDto(newDistrict);
    }

    @Override
    public DistrictNewAndEditDto updateDistrict(Long id, DistrictNewAndEditDto districtNewAndEditDto) {
        return updateAndReturnDto(id, districtNewAndEditDto);
    }

    private DistrictNewAndEditDto updateAndReturnDto(Long id, DistrictNewAndEditDto districtNewAndEditDto) {
        District district = districtRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        district.setDistrictName(districtNewAndEditDto.getDistrictName());
        iterateProjectsInDistrict(district, district.getProjects());
        if (districtNewAndEditDto.getProjects().size() != 0) {
            districtNewAndEditDto.getProjects().iterator().forEachRemaining(project -> district
                    .addProjectToDistrict(projectRepository
                            .findProjectByProjectName(project.getProjectName())
                            .orElseThrow(ResourceNotFoundException::new)));
        }
        districtRepository.save(district);
        return districtNewAndEditMapper.toDistrictDto(district);
    }

    private void iterateProjectsInDistrict(District district, Set<Project> set) {
        for (Iterator<Project> i = set.iterator(); i.hasNext(); ) {
            Project element = i.next();
            district.removeProjectFromDistrictIterate(element);
            i.remove();
            projectRepository.save(element);
        }
    }

}
