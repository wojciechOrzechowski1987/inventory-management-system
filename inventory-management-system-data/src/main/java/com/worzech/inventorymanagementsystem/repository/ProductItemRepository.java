package com.worzech.inventorymanagementsystem.repository;


import com.worzech.inventorymanagementsystem.domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findProductItemByVendorIsNull();

    List<ProductItem> findProductItemByPopcMaterialIsNull();

    @Query("Select p from ProductItem p where p.popcMaterial.id= :id or p.popcMaterial.id is null ")
    List<ProductItem> findPopcMaterialProjectItemsAndNullProjectItems(@Param("id") Long id);

    @Query("Select p from ProductItem p where p.vendor.id= :id or p.vendor.id is null ")
    List<ProductItem> findVendorProjectItemsAndNullProjectItems(@Param("id") Long id);

    Optional<ProductItem> findProductItemByProductItemName(String productItemName);
}
