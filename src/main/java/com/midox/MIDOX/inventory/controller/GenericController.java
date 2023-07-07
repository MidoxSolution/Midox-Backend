package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generic")
public class GenericController {
    @Autowired
    private IGenericService service;

    @GetMapping("/dropdowns")
    public ResponseEntity<List<List<String>>> getDropdowns() {
        ResponseEntity<List<List<String>>> response = null;
        List<List<String>> dropdownsList = service.getAllDropdowns();
        response = new ResponseEntity<>(dropdownsList, HttpStatus.OK);
        return response;
    }

    //TODO
}
