package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.service.IStockHistoryService;
import com.midox.MIDOX.inventory.util.Message;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stockHistory")
public class StockHistoryController {

    @Autowired
    private final IStockHistoryService stockHistoryService;

    @PostMapping("/save")
    public ResponseEntity<Message> addStock(@RequestBody List<StockHistory> stockHistories) {
        ResponseEntity<Message> response = null;
        try {
            Boolean booleanValue = stockHistoryService.addStockHistory(stockHistories);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADDED), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADD_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStockHistory(@PathVariable int id) {
        ResponseEntity<?> response = null;
        try {
            Optional<StockHistory> stockHistory = stockHistoryService.getStockHistry(id);
            if (ValidationUtil.isNotNull(stockHistory)) {
                response = new ResponseEntity<StockHistory>(stockHistory.get(), HttpStatus.OK);
            } else {
                response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_HISTORY_NOT_FOUND), HttpStatus.BAD_REQUEST);

            }
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return (ResponseEntity<Message>) response;
    }
}
