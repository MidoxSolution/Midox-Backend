package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.entity.Supplier;

import java.util.List;

public interface ISupplierService {
    Supplier addSupplier(Supplier supplier);
    List<Supplier> getSupplierByName(String supplierName);

    Supplier getSupplierById(Integer supplierId);
}
