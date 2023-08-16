package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.models.StockHistorySearchCriteria;

import java.util.List;

public interface IStockHistoryService {
    public Boolean addStockHistory(List<StockHistory> stockHistories);

    List<StockHistory> getStockHistories(int id);

    List<StockHistory> getStockHistoryFromCriteria(StockHistorySearchCriteria searchCriteria);

    StockHistory createStockHistory(StockHistory stockHistory);

}
