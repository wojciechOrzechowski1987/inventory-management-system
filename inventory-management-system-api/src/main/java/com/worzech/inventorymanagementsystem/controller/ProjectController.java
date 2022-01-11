package com.worzech.inventorymanagementsystem.controller;

import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.model.project.ProjectBasicDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectNewAndEditDto;
import com.worzech.inventorymanagementsystem.model.project.ProjectOrderDto;
import com.worzech.inventorymanagementsystem.service.project.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "project", produces = "application/json")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping(path = "enums")
    @PreAuthorize("hasAuthority('project:read')")
    public List<Status> getAllProjectStatus() {
        return projectService.getAllProjectStatus();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('project:read')")
    public List<ProjectNewAndEditDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(path = "/basic")
    @PreAuthorize("hasAuthority('project:read')")
    public List<ProjectBasicDto> getAllProjectsBasic() {
        return projectService.getAllProjectsBasic();
    }

    @GetMapping(path = "/demanded")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProjectOrderDto> getAllProjectsStatusDemanded() {
        return projectService.getAllProjectsStatusDemanded();
    }

    @GetMapping(path = "/noDistrict")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProjectBasicDto> getNoDistrictProjects() {
        return projectService.getNoDistrictProjects();
    }

    @GetMapping("/{id}/projectsForEdit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProjectBasicDto> getProjectsForEdit(@PathVariable Long id) {
        return projectService.getProjectsForDistrictEdit(id);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('project:read')")
    @ResponseStatus(HttpStatus.OK)
    public ProjectNewAndEditDto getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/basic/projectName/{projectName}")
    @PreAuthorize("hasAuthority('project:read')")
    @ResponseStatus(HttpStatus.OK)
    public ProjectBasicDto findProjectByName(@PathVariable String projectName) {
        return projectService.getProjectByName(projectName);
    }

    @DeleteMapping("/delete/{projectId}")
    @PreAuthorize("hasAuthority('project:delete')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @PostMapping(path = "/newProject", consumes = "application/json")
    @PreAuthorize("hasAuthority('project:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectNewAndEditDto createNewProject(@RequestBody ProjectNewAndEditDto projectDto) {
        return projectService.createNewProject(projectDto);
    }

    @PutMapping(path = "/editProject/{id}", consumes = "application/json")
    @PreAuthorize("hasAuthority('project:update')")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectNewAndEditDto editProject(@PathVariable Long id, @RequestBody ProjectNewAndEditDto projectDto) {
        return projectService.updateProject(id, projectDto);
    }

}
