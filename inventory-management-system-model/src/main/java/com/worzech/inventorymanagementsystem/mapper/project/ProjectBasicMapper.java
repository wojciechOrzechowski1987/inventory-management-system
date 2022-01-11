package com.worzech.inventorymanagementsystem.mapper.project;

import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.model.project.ProjectBasicDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface ProjectBasicMapper {

    @Mapping(target = "projectCode", source = "project.projectCode")
    @Mapping(target = "projectName", source = "project.projectName")
    @Mapping(target = "district", source = "district.districtName")
    ProjectBasicDto toBasicProjectDto(Project project);

    @InheritInverseConfiguration
    Project fromBasicProjectDto(ProjectBasicDto projectDto);


}
