package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    String findAllByNameQuery = "select * from supplier where supplier_name ILIKE %:supplier_name%";

    @Query(value = findAllByNameQuery, nativeQuery = true)
    List<Supplier> findSupplierByName(@Param("supplier_name") String supplierName);

}
