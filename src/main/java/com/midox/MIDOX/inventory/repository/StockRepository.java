package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Stock;
import org.hibernate.JDBCException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    String stockListQuery = "SELECT stock_no, material.sub_category, material.measurement_type,\n" +
            " quantity, material.supplier FROM public.stock \n" +
            " inner join material on stock.material = material.material_id \n" +
            " ORDER BY stock_no ASC";

    String findStockQuery = " SELECT s.stock_no, m.material_name, s.material, s.quantity FROM public.stock s inner join public.material m \n" +
            " ON m.material_id=s.material WHERE m.material_name = :materialName ";
    String updateStockQuery = "UPDATE public.stock SET quantity= :quantity WHERE material = :materialId";
    String countStockQuery = "SELECT CAST(COUNT(*) as int) FROM public.stock";

    @Query(value = stockListQuery, nativeQuery = true)
    List<Stock> getStockList();

    @Query(value = findStockQuery, nativeQuery = true)
    List<Stock> findStock(@Param("materialName") String materialName);

    @Query(value = countStockQuery, nativeQuery = true)
    int countStock();

    @Query(value = updateStockQuery, nativeQuery = true)
    void updateStockCount(@Param("materialId") int materialId, @Param("quantity") int quantity) throws JDBCException;
}
