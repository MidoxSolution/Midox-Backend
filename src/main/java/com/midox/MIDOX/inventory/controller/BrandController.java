package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.models.GenericSearchCriteria;
import com.midox.MIDOX.inventory.service.spi.IBrandService;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    IBrandService brandService;

    @PostMapping("/add")
    public ResponseEntity<Message> addBrand(@RequestBody Brand brand) {
        ResponseEntity<Message> response = null;
        try {
            brandService.addBrand(brand);
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADDED), HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<Message>(new Message(ConfigConstants.Messages.BRAND_ADD_OPERATION_FAILED), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get-brands")
    public ResponseEntity<List<Brand>> getBrands(@RequestBody GenericSearchCriteria searchCriteria) {
        ResponseEntity<List<Brand>> response = null;
        try {
            List<Brand> brands = brandService.getBrandsByCriteria(searchCriteria.getName(), searchCriteria.getId());
            response = new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<List<Brand>>(new ArrayList<>(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/get/{brandId}")
    public ResponseEntity<Brand> getSupplierDetails(@PathVariable Integer brandId) {
        ResponseEntity<Brand> response = null;
        try {
            Brand brand = brandService.getBrandById(brandId);
            response = new ResponseEntity<Brand>(brand, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<Brand>(new Brand(), HttpStatus.OK);
            e.printStackTrace();
        }
        return response;
    }
}
