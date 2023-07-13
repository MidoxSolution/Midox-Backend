package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.StockHistory;

import java.util.List;
import java.util.Optional;

public interface IStockHistoryService {
    //rename it to add stockhistory
    public Boolean addStockHistory(List<StockHistory> stockHistories);

    // create retrival of all stockhistory of a particular stock
    Optional<StockHistory> getStockHistry(int id);

}
