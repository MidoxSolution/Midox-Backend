package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.models.StockModel;

import java.util.List;

public interface IBrandService {
    Brand addBrand(Brand brand);
    List<Brand> getBrandByName(String brandName);
}
