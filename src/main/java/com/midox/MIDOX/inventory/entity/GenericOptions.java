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
public class GenericOptions {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dropdown_generator")
    @SequenceGenerator(name = "dropdown_generator", sequenceName = "dropdown_seq", allocationSize = 1)
    @Column(name = "drop_id",unique = true)
    private Integer dropId;

    @NonNull
    @Column(name = "drop_type")
    private String dropType;

    @NonNull
    @Column(name = "drop_key")
    private String dropKey;

    @NonNull
    @Column(name = "drop_value")
    private String dropValue;
}
