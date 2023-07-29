package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.StockHistory;

import java.util.List;
import java.util.Optional;

public interface IStockHistoryService {
    public Boolean addStockHistory(List<StockHistory> stockHistories);

    List<StockHistory> getStockHistories(int id);

    StockHistory createStockHistory(StockHistory stockHistory);

}
