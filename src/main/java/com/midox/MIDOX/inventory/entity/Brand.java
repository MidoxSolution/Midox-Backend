package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Builder
public class Brand extends AbstractDataEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_id_generator")
    @SequenceGenerator(name = "brand_id_generator", sequenceName = "brand_sequence", allocationSize = 1)
    @Column(name = "brand_id", unique = true)
    private Integer brandId;

    @NonNull
    @Column(name = "brand_name")
    String brandName;

    @NonNull
    String address;

    @NonNull
    @Column(name = "brand_UID")
    String brandUID;

    @Override
    public void setDefaultValues(){
        super.setDefaultValues();
    }
}
