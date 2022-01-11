package com.worzech.inventorymanagementsystem.repository;

import com.worzech.inventorymanagementsystem.domain.PurchaseProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductItemRepository extends JpaRepository<PurchaseProductItem, Long> {
}
