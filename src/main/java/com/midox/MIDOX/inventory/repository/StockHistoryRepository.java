package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {
}
