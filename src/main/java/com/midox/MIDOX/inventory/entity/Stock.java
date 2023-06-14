package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bill_no",unique = true)
    private Integer billNo;

    @NonNull
    @Column(name = "bill_date")
    private String billDate;

    @NonNull
    @Column(name = "packing_slip_no")
    private Integer packingSlipNo;

    @NonNull
    private String supplier;

    @NonNull
    private String material;

    @NonNull
    @Column(name = "sub_category")
    private String subCategory;

    @NonNull
    @Column(name = "color_fabric_code")
    private String colorFabricCode;

    @NonNull
    @Column(name = "measurement_type")
    private String measurementType;

    @NonNull
    private Integer quantity;

    @NonNull
    private Integer amount;

}
