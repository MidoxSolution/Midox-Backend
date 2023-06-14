package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImpl implements IStockService{

    @Autowired
    private StockRepository repo;
    @Override
    public Integer addStock(Stock stock) {
        return repo.save(stock).getBillNo();
    }

    @Override
    public List<Stock> getAllStocks() {
        return null;
    }
}
