package com.worzech.inventorymanagementsystem.service.project;

import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.model.project.ProjectBasicDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectOrderDto;

import java.util.List;

public interface ProjectService {

    List<Status> getAllProjectStatus();

    List<ProjectNewAndEditDto> getAllProjects();

    List<ProjectBasicDto> getAllProjectsBasic();

    List<ProjectOrderDto> getAllProjectsStatusDemanded();

    List<ProjectBasicDto> getNoDistrictProjects();

    List<ProjectBasicDto> getProjectsForDistrictEdit(Long id);

    ProjectNewAndEditDto getProjectById(Long id);

    ProjectBasicDto getProjectByName(String projectName);

    void deleteProjectById(Long id);

    ProjectNewAndEditDto createNewProject(ProjectNewAndEditDto projectDto);

    ProjectNewAndEditDto updateProject(Long id, ProjectNewAndEditDto projectDto);

}
