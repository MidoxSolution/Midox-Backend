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
@Table(name = "stock_history")
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_history_generator")
    @SequenceGenerator(name = "stock_history_generator", sequenceName = "stock_history_seq", allocationSize = 1)
    @Column(name = "stock_history_id",unique = true)
    private Integer stockHistoryId;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock")
    private Stock stock;

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
