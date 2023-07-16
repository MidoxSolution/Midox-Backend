package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Material;
import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.repository.StockHistoryRepository;
import com.midox.MIDOX.inventory.repository.StockRepository;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements IStockService {


    private final DataSource dataSource;
    private final StockRepository stockRepo;
    private final StockHistoryRepository stockHistoryRepo;

    @Override
    public Boolean addStock(StockHistory stockHistory) {

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
    public void updateStockCount(List<Stock> stockList, StockHistory stockHistory) {

        Material material = new Material();
        AtomicInteger existingQuantity = new AtomicInteger();
        AtomicInteger stockNo = new AtomicInteger();
        Float amount = stockHistory.getAmount();
        AtomicReference<String> userId = new AtomicReference<>("");
        stockList.stream().filter(stockStream -> {
            stockNo.set(stockStream.getStockNo());
            userId.set(stockStream.getMaterial().getCreatedBy());
            material.setMaterialName(stockStream.getMaterial().getMaterialName());
            material.setMaterialId(stockStream.getMaterial().getMaterialId());
            existingQuantity.set(stockStream.getQuantity());
            return true;
        }).collect(Collectors.toList());
        int updatedQuantity = existingQuantity.intValue() + stockHistory.getQuantity();
        if (ValidationUtil.isNotNull(material.getMaterialName())) {
            try {
                int stockHistoryId = stockHistoryRepo.findLatestStockHistoryId();

                try {
                    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
                            .withSchemaName(ConfigConstants.DatabaseConstants.SCHEMA_NAME)
                            .withFunctionName(ConfigConstants.FUNCTION_UPDATE_STOCK.FUNCTION_UPDATE_STOCK_NAME)
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.STOCK_HISTORY_ID, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.STOCK, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.AMOUNT, Types.NUMERIC))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.INSERT_QUANTITY, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.UPDATE_QUANTITY, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.PACKING_SLIP_NUMBER, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.MATERIAL_ID, Types.INTEGER))
                            .declareParameters(new SqlParameter(ConfigConstants.FUNCTION_UPDATE_STOCK.USER_ID, Types.VARCHAR))
                            .withoutProcedureColumnMetaDataAccess()
                            .withReturnValue();
                    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.STOCK_HISTORY_ID, stockHistoryId + 1)
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.STOCK, stockNo.intValue())
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.AMOUNT, amount)
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.INSERT_QUANTITY, stockHistory.getQuantity())
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.UPDATE_QUANTITY, updatedQuantity)
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.PACKING_SLIP_NUMBER, stockHistory.getPackingSlipNo())
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.MATERIAL_ID, material.getMaterialId())
                            .addValue(ConfigConstants.FUNCTION_UPDATE_STOCK.USER_ID, userId.get());
                    simpleJdbcCall.executeFunction(void.class, sqlParameterSource);


                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                }

            } catch (JpaSystemException e) {
                e.getRootCause();
            }
        }

    }
}
