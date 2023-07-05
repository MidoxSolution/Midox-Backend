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
public class Stock{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_no_generator")
    @SequenceGenerator(name = "stock_no_generator", sequenceName = "stock_seq", allocationSize = 1)
    @Column(name = "stock_no",unique = true)
    private Integer stockNo;

    @NonNull
    @Column(name = "bill_date")
    private String billDate;

    @NonNull
    @Column(name = "packing_slip_no")
    private Integer packingSlipNo;

    @NonNull
    private String supplier;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    private Material material;

    @NonNull
    @Column(name = "measurement_type")
    private String measurementType;

    @NonNull
    private Integer quantity;

    @NonNull
    private Integer amount;

}
