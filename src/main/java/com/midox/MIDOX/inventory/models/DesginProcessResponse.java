package com.midox.MIDOX.inventory.models;

import com.midox.MIDOX.inventory.constants.TextileEnum;
import com.midox.MIDOX.inventory.entity.AbstractDataEntity;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DesginProcessResponse extends AbstractDataEntity implements ResponseWrapper{
    private Integer designId;
    EntityCode processCd;


    Integer priority;


    private Double wages;


    String details;


    private TextileEnum.Status status;
}
