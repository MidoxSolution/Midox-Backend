package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {
//    String insertStockHistoryQuery = "INSERT into public.stock_history( " +
//            "  stock_history_id, amount, created_at, created_by, packing_slip_no, quantity, updated_at, updated_by, stock) " +
//            " VALUES (:stockHistoryId, :amount, :timestamp, :userId, :packingSlipNo, :quantity, :timestamp, :userId, :stock);";

    String findLatestStockHistoryIdQuery = "SELECT max(stock_history_id) FROM stock_history;";

//    @Query(value = insertStockHistoryQuery, nativeQuery = true)
//    void insertStockHistory(@Param("stockHistoryId") Integer stockHistoryId, @Param("amount") Float amount, @Param("userId") String userId, @Param("quantity") Integer quantity,
//                            @Param("packingSlipNo") Integer packingSlipNo, @Param("timestamp") Timestamp timestamp, @Param("stock") Integer stock) throws RuntimeException;

    @Query(value = findLatestStockHistoryIdQuery, nativeQuery = true)
    int findLatestStockHistoryId();
}
