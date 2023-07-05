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
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pattern_id_generator")
    @SequenceGenerator(name = "pattern_id_generator", sequenceName = "pattern_seq", allocationSize = 1)
    @Column(name = "pattern_id",unique = true)
    private Integer patternId;

    @NonNull
    @Column(name = "pattern_name")
    private String patternName;
}
