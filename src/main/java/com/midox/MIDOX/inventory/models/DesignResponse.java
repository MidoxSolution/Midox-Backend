package com.midox.MIDOX.inventory.models;

import com.midox.MIDOX.inventory.constants.TextileEnum;
import com.midox.MIDOX.inventory.entity.AbstractDataEntity;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DesignResponse extends AbstractDataEntity implements ResponseWrapper{
    private Integer designId;
    String designNo;

    String brandId;

    EntityCode productCd;

    String details;

    private TextileEnum.Status status;

    List<DesignProcessResponse> processes;
}
