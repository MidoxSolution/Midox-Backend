package com.midox.MIDOX.inventory.models;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DesignSearchCriteria implements  RequestWrapper{

    Integer designId;
    String designNo;
    Integer brandId;
    String productCd;
}
