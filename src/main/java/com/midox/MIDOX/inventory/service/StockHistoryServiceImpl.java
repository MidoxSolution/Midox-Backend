package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.repository.StockHistoryRepository;
import com.midox.MIDOX.inventory.repository.StockRepository;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockHistoryServiceImpl implements IStockHistoryService {

    private final StockHistoryRepository stockHistoryRepo;
    private final StockRepository stockRepo;

    private final IStockService stockService;

    @Override
    public Boolean addStockHistory(List<StockHistory> stockHistories) {
        for (StockHistory stockHistory : stockHistories) {

            if (stockRepo.countStock() == 0) {
                Integer stockHistoryId = stockHistoryRepo.save(stockHistory).getStockHistoryId();

            } else {
                List<Stock> stock = stockRepo.findStock(stockHistory.getStock().getMaterial().getMaterialName());
                if (ValidationUtil.isNotEmpty(stock)) {
                    stockService.updateStockCount(stock, stockHistory.getQuantity());
                } else {
                    stockService.addStock(stockHistory);
                }
            }

        }
        return true;
    }

    @Override
    public Optional<StockHistory> getStockHistry(int id) {
        return stockHistoryRepo.findById(id);
    }
}
