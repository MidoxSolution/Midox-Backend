package com.midox.MIDOX.inventory.entity;

import com.midox.MIDOX.inventory.constants.TextileEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Builder
public class AddaBundle extends AbstractDataEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adda_bundle_id_generator")
    @SequenceGenerator(name = "adda_bundle_id_generator", sequenceName = "adda_bundle_sequence", allocationSize = 1)
    @Column(name = "bundle_id", unique = true)
    private Integer bundleId;

    @NonNull
    @Column(name = "pattern_id")
    Integer patternId;

    @NonNull
    @Column(name = "bundle_name")
    String bundleName;

    @NonNull
    @Column
    String color;

    @NonNull
    @Enumerated(EnumType.STRING)
    private TextileEnum.ProcStatus status;


    @NonNull
    @Column(name = "bundle_size")
    Integer bundleSize;

    @NonNull
    @Column(name = "quantity")
    Double quantity;

    @Override
    public void setDefaultValues(){
        super.setDefaultValues();
    }
}

