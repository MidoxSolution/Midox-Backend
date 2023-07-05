package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.repository.StockRepository;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockRepository repo;

   /* @Autowired
    private MaterialRepository materialRepository;*/

    private ValidationUtil validationUtil;

    @Override
    public Boolean addStock(List<Stock> stockList) {
        for (Stock stock : stockList) {
          //  Material material = stock.getMaterial();
            Integer stockNo = repo.save(stock).getStockNo();
        }
        return true;
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stockList1 = repo.getStockList();
        List<Stock> stockList = new ArrayList<>();
      //  stockList.a
         return stockList1;
    }
}
