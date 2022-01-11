package com.worzech.inventorymanagementsystem.mapper.project;

import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.mapper.district.DistrictNewAndEditMapper;
import com.worzech.inventorymanagementsystem.model.project.ProjectNewAndEditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(uses = {DistrictNewAndEditMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface ProjectNewAndEditMapper {

    @Mapping(target = "projectCode", source = "project.projectCode")
    @Mapping(target = "projectName", source = "project.projectName")
    @Mapping(target = "district", source = "district.districtName")
    ProjectNewAndEditDto toNewAndEditProjectDto(Project project);

    @InheritInverseConfiguration
    Project fromNewAndEditProjectDto(ProjectNewAndEditDto project2Dto);


}
