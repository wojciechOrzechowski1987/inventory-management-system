package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand, Long> {

    @Query("SELECT o FROM Demand o INNER JOIN Project p ON (o.project.id = p.id) " +
            "INNER JOIN District d ON (d.id = p.district.id ) WHERE d.owner = ?#{authentication.name}")
    List<Demand> findDemandForCoordinators();

    Demand getDemandByDemandName(String demandName);

}
