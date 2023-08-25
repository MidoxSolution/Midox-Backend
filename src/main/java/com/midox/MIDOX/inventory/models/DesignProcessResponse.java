package com.midox.MIDOX.inventory.models;

import com.midox.MIDOX.inventory.constants.TextileEnum;
import com.midox.MIDOX.inventory.entity.AbstractDataEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DesignProcessResponse extends AbstractDataEntity implements ResponseWrapper{
    private Integer designId;
    EntityCode processCd;


    Integer priority;


    private Double wages;


    String details;


    private TextileEnum.Status status;
}
