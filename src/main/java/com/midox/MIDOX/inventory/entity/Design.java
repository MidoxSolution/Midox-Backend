package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Builder
public class Design extends AbstractDataEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "design_id_generator")
    @SequenceGenerator(name = "design_id_generator", sequenceName = "design_sequence", allocationSize = 1)
    @Column(name = "design_id", unique = true)
    private Integer designId;

    @NonNull
    @Column(name = "design_no")
    String designNo;

    @NonNull
    String address;

    @NonNull
    @Column(name = "brand_UID")
    String brandUID;

    @NonNull
    @Column(name = "product_cd")
    String productCd;

    @NonNull
    @Column(name = "details")
    String details;

    @Override
    public void setDefaultValues(){
        super.setDefaultValues();
    }
}
