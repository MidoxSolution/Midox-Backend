package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.entity.GenericGroupTypes;
import com.midox.MIDOX.inventory.entity.GenericOptions;
import com.midox.MIDOX.inventory.service.IGenericService;
import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generic")
public class GenericController {
    @Autowired
    private IGenericService service;

    @GetMapping("/dropdowns")
    public ResponseEntity<Map<GenericGroupTypes, List<GenericOptions>>> getDropdowns() {
        ResponseEntity<Map<GenericGroupTypes, List<GenericOptions>>> response = null;
        Map<GenericGroupTypes, List<GenericOptions>> dropdownsList = service.getAllDropdowns();
        response = new ResponseEntity<>(dropdownsList, HttpStatus.OK);
        return response;
    }

    @PostMapping("/dropdown/save")
    public ResponseEntity<Message> addGenericOption(@RequestBody List<GenericOptions> genericOptions) {
        ResponseEntity<Message> response = null;
        service.addDropdownValue(genericOptions);
        response = new ResponseEntity<>(new Message(ConfigConstants.Messages.GENERIC_OPTIONS_ADDED), HttpStatus.OK);
        return response;
    }
}
