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
// create similar entity for drop_type - which would act as a group key
public class GenericOptions extends Audit{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generic_id_generator")
    @SequenceGenerator(name = "generic_id_generator", sequenceName = "generic_options_seq", allocationSize = 1)
    @Column(name = "generic_id",unique = true)
    private Integer genericId;

    @NonNull
    @Column(name = "generic_group_type")
    private String genericGroupType;

    @NonNull
    @Column(name = "dropdown_key")
    private String dropdownKey;

    @NonNull
    @Column(name = "dropdown_value")
    private String dropdownValue;
}
