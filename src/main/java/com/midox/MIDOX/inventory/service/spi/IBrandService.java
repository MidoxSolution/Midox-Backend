package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Brand;

import java.util.List;

public interface IBrandService {
    Brand addBrand(Brand brand);
    List<Brand> getBrandsByName(String brandName);
    Brand getBrandById(Integer brandId);
}
