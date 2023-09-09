package com.midox.MIDOX.inventory.models.RequestModels;

import com.midox.MIDOX.inventory.models.RequestWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AddaBundleSearchCriteria implements RequestWrapper {

    Integer bundleId;
    String bundleName;
    String processCd;
    Integer patternId;
    Integer employeeId;
}
