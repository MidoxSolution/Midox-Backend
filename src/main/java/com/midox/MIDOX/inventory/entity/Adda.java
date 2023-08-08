package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Builder
public class Adda extends AbstractDataEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adda_id_generator")
    @SequenceGenerator(name = "adda_id_generator", sequenceName = "adda_sequence", allocationSize = 1)
    @Column(name = "adda_id", unique = true)
    private Integer addaId;

    @NonNull
    @Column(name = "adda_no")
    String addaNo;

    @NonNull
    @Column(name = "brand_id")
    Integer brandId;

    @NonNull
    @Column(name = "product_cd")
    String productCd;

    @NonNull
    @Column(name = "design_id")
    Integer designId;

    @NonNull
    @Column(name = "quantity")
    Double quantity;

    @Column(name = "completion_date")
    Date completionDate;

    @Override
    public void setDefaultValues(){
        super.setDefaultValues();
    }
}

