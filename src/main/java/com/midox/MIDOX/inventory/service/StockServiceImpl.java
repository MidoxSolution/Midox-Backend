package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.DAO.StockDao;
import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.repository.StockRepository;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private final StockDao stockDao;

    private IStockHistoryService stockHistoryService;


    private ValidationUtil validationUtil;

    @Override
    public Boolean addStock(List<Stock> stockList) {
        //this should update the total available quantity of stock in stock table also - apart from creating entry in stock history
        for (Stock stock : stockList) {
            Integer stockNo = stockRepo.save(stock).getStockNo();
            stockHistoryService.addStock(stock);
        }
        return true;
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stockList = stockDao.getStockList();
        return stockList;
    }
}
