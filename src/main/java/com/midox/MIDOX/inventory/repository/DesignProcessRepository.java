package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Design;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignProcessRepository extends JpaRepository<DesignProcess, Integer> {

    String findAllByBrandNameQuery = "select * from brand where (:brand_name is null or brand_name ILIKE %:brand_name%)" +
            " and (:brand_id is null or brand_id = :brand_id)";

    @Query(value = findAllByBrandNameQuery, nativeQuery = true)
    List<Design> findBrandsByCriteria(@Param("brand_name") String brandName, @Param("brand_id") Integer brandId);

    List<DesignProcess> findAllByDesignId(Integer designId);
}
