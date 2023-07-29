package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    String stockListQuery = "SELECT s.* from stock s " +
            " ORDER BY updated_at DESC";

    String findStockQuery = " SELECT s.* FROM stock s \n" +
            " WHERE s.material_cd = :materialCd " +
            "AND s.subcategory_cd = :subcategoryCd " +
            "AND s.color_fabric_cd = :colorFabricCd ";
    //    String updateStockQuery = "UPDATE public.stock SET quantity= :quantity WHERE material = :materialId";

    String insertStockQuery = "INSERT into stock( " +
            "  material_cd, subcategory_cd, color_fabric_cd, unit) " +
            " VALUES (:materialCd, :subcategoryCd, :colorFabricCd, :unit);";

    String countStockQuery = "SELECT CAST(COUNT(*) as int) FROM public.stock";

    @Query(value = stockListQuery, nativeQuery = true)
    List<Stock> getStockList();

    @Query(value = findStockQuery, nativeQuery = true)
    Stock findStock(@Param("materialCd") String materialCd,
                          @Param("subcategoryCd") String subcategoryCd,
                          @Param("colorFabricCd") String colorFabricCd);

    @Query(value = countStockQuery, nativeQuery = true)
    int countStock();

    @Query(value = insertStockQuery, nativeQuery = true)
    void insertStock(@Param("materialCd") String materialCd,
                     @Param("subcategoryCd") String subcategoryCd,
                     @Param("colorFabricCd") String colorFabricCd,
                     @Param("unit") String unit) throws JpaSystemException;
//    @Query(value = updateStockQuery, nativeQuery = true)
//    void updateStockCount(@Param("materialId") int materialId, @Param("quantity") int quantity) throws JpaSystemException;
}
