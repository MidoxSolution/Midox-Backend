package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

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

    // Check it, this should be available at material level. I think this would be redundant.
    @NonNull
    private String supplier;

    // This could be kept at stock level - think about it.
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

    @NonNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @NonNull
    @Column(name = "created_by")
    private String createdBy;

    @NonNull
    @Column(name = "updated_by")
    private String updatedBy;
}
