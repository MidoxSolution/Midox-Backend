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
public class Stock extends AbstractDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_no_generator")
    @SequenceGenerator(name = "stock_no_generator", sequenceName = "stock_seq", allocationSize = 1)
    @Column(name = "stock_no", unique = true)
    private Integer stockNo;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "material", unique = true)
    private Material material;

    @NonNull
    private Integer quantity;

}
