package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.StockHistory;

import java.util.List;
import java.util.Optional;

public interface IStockHistoryService {
    public Boolean addStockHistory(List<StockHistory> stockHistories);

    Optional<StockHistory> getStockHistry(int id);

}
