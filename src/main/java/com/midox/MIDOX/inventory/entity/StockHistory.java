package com.midox.MIDOX.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class StockHistory extends AbstractDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_history_generator")
    @SequenceGenerator(name = "stock_history_generator", sequenceName = "stock_history_seq", allocationSize = 1)
    @Column(name = "stock_history_id", unique = true)
    private Integer stockHistoryId;

    @NonNull
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock", unique = true)
    private Stock stock;

    @NonNull
    @Column(name = "packing_slip_no")
    private Integer packingSlipNo;

    @NonNull
    private Integer quantity;

    @NonNull
    private Float amount;

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
