package com.midox.MIDOX.inventory.models;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DesginSearchCriteria implements  RequestWrapper{

    Integer desginId;
    String designNo;
    Integer brandId;
    String productCd;
}
