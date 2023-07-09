package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
public class Stock extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_no_generator")
    @SequenceGenerator(name = "stock_no_generator", sequenceName = "stock_seq", allocationSize = 1)
    @Column(name = "stock_no",unique = true)
    private Integer stockNo;

    // Cannot be held at stock level, as this would be unique for every bill - whereas stock is a total available quantity of stock.
    @NonNull
    @Column(name = "bill_date")
    private String billDate;

    // same as billDate - shouldn't be here
    @NonNull
    @Column(name = "packing_slip_no")
    private Integer packingSlipNo;

    @NonNull
    private String supplier;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    private Material material;

    // Check it - can be kept at material level ..?
    @NonNull
    @Column(name = "measurement_type")
    private String measurementType;

    @NonNull
    private Integer quantity;

    // Again same thing - this belongs to every specfic entry of stock purchase and cannot exist here
    @NonNull
    private Integer amount;

}
