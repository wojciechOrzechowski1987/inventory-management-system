package com.worzech.inventorymanagementsystem.mapper.project;

import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.mapper.demand.DemandNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.project.ProjectOrderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {DemandNewAndEditMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface ProjectOrderMapper {

    @Mapping(target = "projectCode", source = "project.projectCode")
    @Mapping(target = "projectName", source = "project.projectName")
    @Mapping(target = "district", source = "district.districtName")
    ProjectOrderDto toOrderProjectDto(Project project);

    @InheritInverseConfiguration
    Project fromOrderProjectDto(ProjectOrderDto projectDto);


}
