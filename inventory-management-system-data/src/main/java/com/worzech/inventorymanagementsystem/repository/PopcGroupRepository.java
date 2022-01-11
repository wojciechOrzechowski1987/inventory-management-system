package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.PopcGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PopcGroupRepository extends JpaRepository<PopcGroup, Long> {

    PopcGroup getPopcGroupByPopcGroupName(String popcGroupName);
}
