package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.PopcMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PopcMaterialRepository extends JpaRepository<PopcMaterial, Long> {

    List<PopcMaterial> findPopcMaterialByPopcSubgroupIsNull();

    Optional<PopcMaterial> findPopcMaterialByPopcMaterialCode(String popcMaterialCode);

    @Query("Select s from PopcMaterial s where s.popcSubgroup.id= :id or s.popcSubgroup.id is null ")
    List<PopcMaterial> getPopcSubgroupPopcMaterialsAndNullMaterials(@Param("id") Long id);
}
