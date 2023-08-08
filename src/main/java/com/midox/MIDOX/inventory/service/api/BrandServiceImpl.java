package com.midox.MIDOX.inventory.service.api;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.repository.BrandRepository;
import com.midox.MIDOX.inventory.service.spi.IBrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

    @Autowired
    BrandRepository brandRepo;

    @Override
    public Brand addBrand(Brand brand){
        brand.setDefaultValues();
        return brandRepo.saveAndFlush(brand);
    }

    @Override
    public List<Brand> getBrandByName(String brandName){
        return  brandRepo.findBrandsByBrandName(brandName);
    }

}
