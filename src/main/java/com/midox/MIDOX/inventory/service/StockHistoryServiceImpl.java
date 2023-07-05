package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.repository.StockHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockHistoryServiceImpl implements IStockHistoryService {

    private StockHistoryRepository repo;

    @Override
    public Boolean addStock(Stock stock) {
        StockHistory stockHistory = new StockHistory();
      //  stockHistory.setStock(stock);
        stockHistory.setAmount(stock.getAmount());
        //stockHistory.setMaterial(stock.getMaterial());
        stockHistory.setQuantity(stock.getQuantity());
        stockHistory.setBillDate(stock.getBillDate());
        stockHistory.setSupplier(stock.getSupplier());
        stockHistory.setMeasurementType(stock.getMeasurementType());
        stockHistory.setPackingSlipNo(stock.getPackingSlipNo());
        Integer stockNo = repo.save(stockHistory).getStockHistoryId();
        return true;
    }
}
