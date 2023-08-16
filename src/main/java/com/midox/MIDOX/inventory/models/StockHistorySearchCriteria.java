package com.midox.MIDOX.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class StockHistorySearchCriteria implements RequestWrapper{

    Timestamp fromDate;
    Timestamp toDate;
    Integer stockId;

}
