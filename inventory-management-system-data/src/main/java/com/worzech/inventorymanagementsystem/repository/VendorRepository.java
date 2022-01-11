package com.worzech.inventorymanagementsystem.repository;


import com.worzech.inventorymanagementsystem.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Vendor findVendorByVendorName(String vendorName);

}
