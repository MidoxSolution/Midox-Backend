package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignRepository extends JpaRepository<Design, Integer> {

    String findAllByCriteriaQuery = "select * from design where (:design_no is null or design_no ILIKE %:design_no%)" +
            " and (:desgin_id is null or desgin_id = :desgin_id) " +
            " and (:brand_id is null or brand_id = :brand_id) " +
            " and (:product_cd is null or product_cd ILIKE %:product_cd%)";

    @Query(value = findAllByCriteriaQuery, nativeQuery = true)
    List<Design> findDesignsByCriteria(@Param("desgin_id") Integer desginId, @Param("design_no") String designNo, @Param("brand_id") Integer brandId, @Param("product_cd") String productCd);

}
