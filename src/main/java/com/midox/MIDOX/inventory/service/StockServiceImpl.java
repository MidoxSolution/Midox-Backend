package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.Material;
import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.repository.StockHistoryRepository;
import com.midox.MIDOX.inventory.repository.StockRepository;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepo;
    private final StockHistoryRepository stockHistoryRepo;

    @Override
    public Boolean addStock(StockHistory stockHistory) {
        //this should update the total available quantity of stock in stock table also - apart from creating entry in stock history

        Integer stockHistoryId = stockHistoryRepo.save(stockHistory).getStockHistoryId();

        return true;
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stockList = stockRepo.getStockList();
        List<Stock> stocks= new ArrayList<>();
        stockList.stream().distinct().forEach(stockIterate -> {
            Stock stock = new Stock();
            stock.setStockNo(stockIterate.getStockNo());
            stock.setQuantity(stockIterate.getQuantity());
            stock.setMaterial(stockIterate.getMaterial());
            stocks.add(stock);
        });
        return stocks;

    }

    @Override
    public void updateStockCount(List<Stock> stockList, Integer quantity) {

        Material material = new Material();
        AtomicInteger existingQuantity = new AtomicInteger();
        stockList.stream().filter(stockStream -> {
            material.setMaterialName(stockStream.getMaterial().getMaterialName());
            material.setMaterialId(stockStream.getMaterial().getMaterialId());
            existingQuantity.set(stockStream.getQuantity());
            return true;
        }).collect(Collectors.toList());
        int updatedQuantity = existingQuantity.intValue() + quantity;
        if (ValidationUtil.isNotNull(material.getMaterialName())) {
            try {
                stockRepo.updateStockCount(material.getMaterialId(), updatedQuantity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
