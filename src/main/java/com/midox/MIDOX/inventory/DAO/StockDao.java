package com.midox.MIDOX.inventory.DAO;

import com.midox.MIDOX.inventory.entity.Stock;

import java.util.List;

public interface StockDao {

    List<Stock> getStockList();
}
