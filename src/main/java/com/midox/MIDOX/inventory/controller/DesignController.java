package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.entity.Design;
import com.midox.MIDOX.inventory.models.DesginSearchCriteria;
import com.midox.MIDOX.inventory.models.GenericSearchCriteria;
import com.midox.MIDOX.inventory.service.spi.IDesignProcessService;
import com.midox.MIDOX.inventory.service.spi.IDesignService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/design")
public class DesignController {

    @Autowired
    IDesignService designService;

    @Autowired
    IDesignProcessService designProcessService;


    @PostMapping("/add")
    public ResponseEntity<Message> addDesign(@RequestBody Design design) {
        ResponseEntity<Message> response = null;
        try {
            designService.addDesign(design);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/add-design-process")
    public ResponseEntity<Message> addDesignWithProcesses(@RequestBody Design design) {
        ResponseEntity<Message> response = null;
        try {
            designService.addDesignWithProcess(design);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/edit")
    public ResponseEntity<Message> editDesign(@RequestBody Design design) {
        ResponseEntity<Message> response = null;
        try {
            designService.editDesign(design);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/update-processes")
    public ResponseEntity<Message> updateDesignProcesses(@RequestBody Design design) {
        ResponseEntity<Message> response = null;
        try {
            designProcessService.updateDesignProcess(design);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/get-designs")
    public ResponseEntity<List<Design>> getDesigns(@RequestBody DesginSearchCriteria searchCriteria) {
        ResponseEntity<List<Design>> response = null;
        try {
            List<Design> designs = designService.getDesignByCriteria(searchCriteria.getDesginId(), searchCriteria.getDesignNo(), searchCriteria.getBrandId(), searchCriteria.getProductCd());
            response = new ResponseEntity<List<Design>>(designs, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<List<Design>>(new ArrayList<>(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    // delete api - including process

    /*@GetMapping("/get/{brandId}")
    public ResponseEntity<Brand> getSupplierDetails(@PathVariable Integer brandId) {
        ResponseEntity<Brand> response = null;
        try {
            Brand brand = designService.getBrandById(brandId);
            response = new ResponseEntity<Brand>(brand, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Brand>(new Brand(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }*/
}
