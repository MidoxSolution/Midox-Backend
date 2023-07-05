package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;

import java.util.List;

public interface IStockService {

    public Boolean addStock(List<Stock> stockList);

    public List<Stock> getAllStocks();
}
