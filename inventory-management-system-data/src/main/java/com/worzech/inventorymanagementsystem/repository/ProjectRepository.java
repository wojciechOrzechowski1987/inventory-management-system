package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.Project;
import com.worzech.inventorymanagementsystem.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT p FROM Project p INNER JOIN District d ON (d.id = p.district.id ) WHERE d.owner = ?#{authentication.name}")
    List<Project> findProjectsForCoordinators();

    List<Project> findProjectByDistrictIsNull();

    List<Project> getProjectsByStatusEquals(Status status);

    @Query("Select p from Project p where p.district.id= :id or p.district.id is null ")
    List<Project> getDistrictProjectsAndNullProjects(@Param("id") Long id);

    Project getProjectByProjectName(String projectName);

    Optional<Project> findProjectByProjectName(String projectName);

}


