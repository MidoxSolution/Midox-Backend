package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Employee;
import com.midox.MIDOX.inventory.entity.StockHistory;
import com.midox.MIDOX.inventory.service.IEmployeeService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private IEmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Message> addEmployee(@RequestBody Employee employee) {
        ResponseEntity<Message> response = null;
        try {
            Boolean booleanValue = employeeService.addEmployee(employee);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADDED), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.STOCK_ADD_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }
}
