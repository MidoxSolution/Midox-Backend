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
// create similar entity for drop_type - which would act as a group key
public class GroupEntity extends AbstractDataEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_generator")
    @SequenceGenerator(name = "entity_id_generator", sequenceName = "entity_sequence", allocationSize = 1)
    @Column(name = "entity_id", unique = true)
    private Integer entityId;

    @NonNull
    @Column(name = "entity_cd", unique = true)
    private String entityCd;

    @Nullable
    @Column(name = "parent_entity_cd")
    private String parentEntityCd;

    @NonNull
    //@JsonBackReference
    //@ManyToOne(fetch = FetchType.LAZY)
    //@Join
    @Column(name = "master_cd")
    private String masterCd;

    @NonNull
    @Column(name = "display_value")
    private String displayValue;
}
