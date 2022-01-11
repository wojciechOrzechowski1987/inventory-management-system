package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.PopcSubgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PopcSubgroupRepository extends JpaRepository<PopcSubgroup, Long> {

    Optional<PopcSubgroup> findPopcSubgroupByPopcSubgroupName(String popcSubgrouptName);

    List<PopcSubgroup> findPopcSubgroupByPopcGroupIsNull();

    @Query("Select s from PopcSubgroup s where s.popcGroup.id is null or s.popcGroup.id= :id ")
    List<PopcSubgroup> findGroupSubgroupsAndNullSubgroups(@Param("id") Long id);

}

