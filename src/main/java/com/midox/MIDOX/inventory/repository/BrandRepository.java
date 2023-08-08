package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    String findAllByBrandNameQuery = "select * from brand where brand_name like %:brand_name%";

    @Query(value = findAllByBrandNameQuery, nativeQuery = true)
    List<Brand> findBrandsByBrandName(@Param("brand_name") String brandName);

}
