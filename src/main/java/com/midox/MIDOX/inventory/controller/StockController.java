package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.service.IStockService;
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
    private IStockService service;

    @GetMapping("/test")
    public ResponseEntity<Message> testApp(){
        ResponseEntity<Message> response = null;
        response = new ResponseEntity<Message>(new Message("MIDOX IS WORKING FINE."), HttpStatus.OK);
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Message> addStock(@RequestBody List<Stock> stockList) {
        ResponseEntity<Message> response = null;
        try {
            Boolean booleanValue = service.addStock(stockList);
            response = new ResponseEntity<Message>(new Message("STOCK ADDED SUCCESSFULLY "), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message("STOCK ADD OPERATION FAILED"), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Stock>> viewStocks(){
        ResponseEntity<List<Stock>> response = null;
        List<Stock> stockList = service.getAllStocks();
        System.out.println("STOCK LIST"+stockList);
        response = new ResponseEntity<>(new ArrayList<>(stockList),HttpStatus.OK);
        return response;
    }
}
