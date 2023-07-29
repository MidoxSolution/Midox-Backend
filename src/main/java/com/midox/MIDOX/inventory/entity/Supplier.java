package com.midox.MIDOX.inventory.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_id_generator")
    @SequenceGenerator(name = "supplier_id_generator", sequenceName = "supplier_sequence", allocationSize = 1)
    @Column(name = "supplier_id", unique = true)
    private Integer supplierId;

    @NonNull
    @Column(name = "supplier_name")
    private String supplierName;
}
