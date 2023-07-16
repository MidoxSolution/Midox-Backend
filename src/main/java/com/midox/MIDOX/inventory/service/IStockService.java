package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;

import java.util.List;

public interface IStockService {

    public Boolean addStock(StockHistory stockHistory);

    public List<Stock> getAllStocks();

    public void updateStockCount(List<Stock> stock, StockHistory stockHistory);
}
