package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query(value = "SELECT stock_no,measurement_type,material.sub_category,\n" +
            "quantity,supplier FROM public.stock \n" +
            "inner join material on stock.material = material.material_id \n" +
            "ORDER BY stock_no ASC", nativeQuery = true)
    List<Stock> getStockList();

}
