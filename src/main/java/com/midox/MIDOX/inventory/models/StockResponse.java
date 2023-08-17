package com.midox.MIDOX.inventory.models;

import com.midox.MIDOX.inventory.entity.AbstractDataEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class StockResponse extends AbstractDataEntity implements ResponseWrapper{
    private Integer stockId;

    private EntityCode materialCd;
    private EntityCode subcategoryCd;

    private EntityCode colorFabricCd;

    private EntityCode unit;

    private Double availableQuantity;
}
