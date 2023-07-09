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
// Add material cd also
public class Material extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_id_generator")
    @SequenceGenerator(name = "material_id_generator", sequenceName = "material_seq", allocationSize = 1)
    @Column(name = "material_id",unique = true)
    private Integer materialId;

    @NonNull
    @Column(name = "material_name")
    private String materialName;

    @NonNull
    @Column(name = "sub_category")
    private String subCategory;

    @NonNull
    @Column(name = "color_fabric_code")
    private String colorFabricCode;

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
