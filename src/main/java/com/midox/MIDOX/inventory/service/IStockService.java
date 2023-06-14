package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;

import java.util.List;

public interface IStockService {

    public Integer addStock(Stock stock);

    public List<Stock> getAllStocks();
}
