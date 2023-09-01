package com.midox.MIDOX.inventory.util.Mapper;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.entity.Supplier;
import com.midox.MIDOX.inventory.models.StockHistoryResponse;
import com.midox.MIDOX.inventory.models.StockResponse;
import com.midox.MIDOX.inventory.service.spi.IStockService;
import com.midox.MIDOX.inventory.service.spi.ISupplierService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
uses = {EntityCodeMapper.class, EmployeeNameMapper.class, StockMapper.class})
public abstract class StockHistoryMapper {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IStockService stockService;


    @Mapping(source = "stockId", target = "stockDetails", qualifiedByName = "mapStockFromId")
    @Mapping(source = "supplierId", target = "supplierDetails", qualifiedByName = "mapSupplierFromId")
    public abstract StockHistoryResponse toStockHistoryResponse(StockHistory stockHistory);

    @Named(value = "mapStockFromId")
    Stock mapStockFromId(Integer stockId) { // return type of gender, I took String. For id took Long
        return stockService.getStockById(stockId);
    }

    @Named(value = "mapSupplierFromId")
    Supplier mapSupplierFromId(Integer supplierId) { // return type of gender, I took String. For id took Long
        return supplierService.getSupplierById(supplierId);
    }
}
