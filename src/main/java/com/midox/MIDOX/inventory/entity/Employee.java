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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id_generator")
    @SequenceGenerator(name = "emp_id_generator", sequenceName = "emp_seq", allocationSize = 1)
    @Column(name = "emp_id",unique = true)
    private Integer empID;

    @NonNull
    @Column(name = "emp_name")
    private String empName;
}
