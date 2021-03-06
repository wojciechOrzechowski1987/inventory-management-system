package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.District;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Override
    @Query("SELECT DISTINCT o FROM District o fetch all properties ")
    @NonNull
    List<District> findAll();

    @Query("SELECT o FROM District o WHERE o.owner = ?#{authentication.name}")
    List<District> findDistrictByOwner();


    Optional<District> findDistrictByDistrictName(String districtName);


}
