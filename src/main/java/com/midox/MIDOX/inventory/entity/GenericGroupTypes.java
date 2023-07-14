package com.midox.MIDOX.inventory.entity;

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
public class GenericGroupTypes extends AbstractDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generic_group_id_generator")
    @SequenceGenerator(name = "generic_group_id_generator", sequenceName = "generic_group_options_seq", allocationSize = 1)
    @Column(name = "generic_group_type_id", unique = true)
    private Integer genericGroupTypeId;

    @NonNull
    @Column(name = "generic_group_type", unique = true)
    private String genericGroupType;

    @NonNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @NonNull
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @NonNull
    @Column(name = "created_by")
    private String createdBy;

    @NonNull
    @Column(name = "updated_by")
    private String updatedBy;
}
