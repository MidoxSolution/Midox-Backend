package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Supplier;
import com.midox.MIDOX.inventory.service.spi.ISupplierService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    ISupplierService supplierService;

    @PostMapping("/add")
    public ResponseEntity<Message> addBrand(@RequestBody Supplier supplier) {
        ResponseEntity<Message> response = null;
        try {
            supplierService.addSupplier(supplier);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.SUPPLIER_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.SUPPLIER_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get-suppliers/{supplierName}")
    public ResponseEntity<List<Supplier>> getSuppliers(@PathVariable String supplierName) {
        ResponseEntity<List<Supplier>> response = null;
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByName(supplierName);
            response = new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<List<Supplier>>(new ArrayList<>(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get/{supplierId}")
    public ResponseEntity<Supplier> getSupplierDetails(@PathVariable Integer supplierId) {
        ResponseEntity<Supplier> response = null;
        try {
            Supplier supplier = supplierService.getSupplierById(supplierId);
            response = new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Supplier>(new Supplier(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }
}
