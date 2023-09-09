package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Adda;
import com.midox.MIDOX.inventory.entity.AddaBundle;
import com.midox.MIDOX.inventory.entity.AddaMaterial;
import com.midox.MIDOX.inventory.entity.AddaPattern;
import com.midox.MIDOX.inventory.models.RequestModels.AddaSearchCriteria;
import com.midox.MIDOX.inventory.models.ResponseModels.AddaResponse;
import com.midox.MIDOX.inventory.service.spi.IAddaBundleService;
import com.midox.MIDOX.inventory.service.spi.IAddaService;
import com.midox.MIDOX.inventory.util.Mapper.AddaMapper;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bundle")
public class AddaBundleController {

    @Autowired
    IAddaBundleService bundleService;

    @PostMapping("/update-status")
    public ResponseEntity<?> updateAddBundleStatus(@RequestBody AddaBundle bundle) {
        ResponseEntity<Message> response = null;
        try {
            bundle = bundleService.updateBundleStatus(bundle.getBundleId(), bundle.getCurrentProcessStatus());
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/assign-employee")
    public ResponseEntity<?> assignBundleToEmployee(@RequestBody AddaBundle bundle) {
        ResponseEntity<Message> response = null;
        try {
            bundle = bundleService.assignBundleToEmployee(bundle.getBundleId(), bundle.getCurrentEmployeeId());
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    // delete api - including process

    /*@GetMapping("/get/{brandId}")
    public ResponseEntity<Brand> getSupplierDetails(@PathVariable Integer brandId) {
        ResponseEntity<Brand> response = null;
        try {
            Brand brand = AddaService.getBrandById(brandId);
            response = new ResponseEntity<Brand>(brand, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Brand>(new Brand(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }*/
}
