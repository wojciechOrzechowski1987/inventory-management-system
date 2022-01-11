package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
