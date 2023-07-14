package com.midox.MIDOX.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class GenericOptions extends AbstractDataEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generic_id_generator")
    @SequenceGenerator(name = "generic_id_generator", sequenceName = "generic_options_seq", allocationSize = 1)
    @Column(name = "generic_id", unique = true)
    private Integer genericId;

    @NonNull
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "generic_group_type", unique = true)
    private GenericGroupTypes genericGroupType;

    @NonNull
    @Column(name = "dropdown_key")
    private String dropdownKey;

    @NonNull
    @Column(name = "dropdown_value")
    private String dropdownValue;

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
