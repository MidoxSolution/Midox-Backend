package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.models.StockModel;

import java.util.List;

public interface IStockService {

    public Boolean addStocks(List<StockModel> stocks);

    List<Stock> getAllStocks();

    Stock getStockById(Integer stockId);

    Stock createStock(Stock stock);

    public void updateStockCount(List<Stock> stock, StockHistory stockHistory);
}
