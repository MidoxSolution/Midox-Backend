package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.models.StockModel;
import com.midox.MIDOX.inventory.service.spi.IStockService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping(value = {"/", "/home","/test"})
    public ResponseEntity<Message> testApp() {
        ResponseEntity<Message> response = null;
        response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STATUS), HttpStatus.OK);
        return response;
    }

   @PostMapping("/save")
    public ResponseEntity<Message> addStocks(@RequestBody List<StockModel> stockList) {
        ResponseEntity<Message> response = null;
        try {
            Boolean booleanValue = stockService.addStocks(stockList);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADDED), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Stock>> viewStocks() {
        ResponseEntity<List<Stock>> response = null;
        List<Stock> stockList = stockService.getAllStocks();
        response = new ResponseEntity<>(new ArrayList<>(stockList), HttpStatus.OK);
        return response;
    }
}
