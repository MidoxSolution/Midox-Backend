package com.midox.MIDOX.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
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
    @SequenceGenerator(name = "stock_history_generator", sequenceName = "stock_history_sequence", allocationSize = 1)
    @Column(name = "stock_history_id", unique = true)
    private Integer stockHistoryId;

    @NonNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Nullable
    @Column(name = "bill_no")
    private String billNo;

    @NonNull
    private Double quantity;

    @NonNull
    private Double amount;

    @Nullable
    @Column(name = "bill_date")
    private Timestamp billDate;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Override
    public void setDefaultValues(){
        super.setDefaultValues();
        this.supplierId = 0 == this.supplierId? 1 : this.supplierId;
    }
}
