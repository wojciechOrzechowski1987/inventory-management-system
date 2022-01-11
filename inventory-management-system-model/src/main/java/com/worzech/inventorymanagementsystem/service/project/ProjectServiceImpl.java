package com.worzech.inventorymanagementsystem.service.project;

import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.exceptions.ResourceNotFoundException;
import com.worzech.inventorymanagementsystem.mapper.project.ProjectBasicMapper;
import com.worzech.inventorymanagementsystem.mapper.project.ProjectNewAndEditMapper;
import com.worzech.inventorymanagementsystem.mapper.project.ProjectOrderMapper;
import com.worzech.inventorymanagementsystem.model.project.ProjectBasicDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectOrderDto;
import com.worzech.inventorymanagementsystem.repository.DistrictRepository;
import com.worzech.inventorymanagementsystem.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectNewAndEditMapper projectNewAndEditMapper;
    private final ProjectBasicMapper projectBasicMapper;
    private final ProjectOrderMapper projectOrderMapper;
    private final ProjectRepository projectRepository;
    private final DistrictRepository districtRepository;

    @Override
    public List<Status> getAllProjectStatus() {
        return Arrays.asList(Status.values());
    }

    @Override
    public List<ProjectNewAndEditDto> getAllProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch((a -> a.getAuthority().equals("ROLE_ADMIN")))) {
            return projectRepository
                    .findAll()
                    .stream()
                    .map(projectNewAndEditMapper::toNewAndEditProjectDto)
                    .collect(Collectors.toList());
        } else {
            return projectRepository
                    .findProjectsForCoordinators()
                    .stream()
                    .map(projectNewAndEditMapper::toNewAndEditProjectDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ProjectBasicDto> getAllProjectsBasic() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch((a -> a.getAuthority().equals("ROLE_ADMIN")))) {
            return projectRepository
                    .findAll()
                    .stream()
                    .map(projectBasicMapper::toBasicProjectDto)
                    .collect(Collectors.toList());
        } else {
            return projectRepository
                    .findProjectsForCoordinators()
                    .stream()
                    .map(projectBasicMapper::toBasicProjectDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ProjectOrderDto> getAllProjectsStatusDemanded() {

        return projectRepository
                .getProjectsByStatusEquals(Status.DEMAND)
                .stream()
                .map(projectOrderMapper::toOrderProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectBasicDto> getNoDistrictProjects() {
        return projectRepository
                .findProjectByDistrictIsNull()
                .stream()
                .map(projectBasicMapper::toBasicProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectBasicDto> getProjectsForDistrictEdit(Long id) {
        return projectRepository
                .getDistrictProjectsAndNullProjects(id)
                .stream()
                .map(projectBasicMapper::toBasicProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectNewAndEditDto getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectNewAndEditMapper::toNewAndEditProjectDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProjectBasicDto getProjectByName(String projectName) {
        return projectRepository.findProjectByProjectName(projectName)
                .map(projectBasicMapper::toBasicProjectDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectNewAndEditDto createNewProject(ProjectNewAndEditDto projectDto) {
        return saveAndReturnDto(projectNewAndEditMapper.fromNewAndEditProjectDto(projectDto));
    }

    private ProjectNewAndEditDto saveAndReturnDto(Project project) {
        Project newProject = new Project();
        newProject.setProjectName(project.getProjectName());
        newProject.setProjectCode(project.getProjectCode());
        newProject.setStatus(Status.NEW);
        if (Objects.equals(project.getDistrict().getDistrictName(), null)) {
        } else {
            newProject.setDistrict(districtRepository.findDistrictByDistrictName(project.getDistrict().getDistrictName()).orElseThrow(ResourceNotFoundException::new));
        }
        projectRepository.save(newProject);

        return projectNewAndEditMapper.toNewAndEditProjectDto(newProject);
    }

    @Override
    public ProjectNewAndEditDto updateProject(Long id, ProjectNewAndEditDto projectDto) {
        return updateAndReturnDto(id, projectDto);
    }

    private ProjectNewAndEditDto updateAndReturnDto(Long id, ProjectNewAndEditDto projectDto) {
        Project project = projectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        project.setProjectName(projectDto.getProjectName());
        project.setProjectCode(projectDto.getProjectCode());
        project.setStatus(projectDto.getStatus());
        project.setDistrict(districtRepository.findDistrictByDistrictName(projectDto.getDistrict()).orElseThrow(ResourceNotFoundException::new));
        projectRepository.save(project);
        return projectNewAndEditMapper.toNewAndEditProjectDto(project);
    }


}
