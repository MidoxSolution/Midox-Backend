package com.midox.MIDOX.inventory.entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.io.Serializable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractDataEntity implements Serializable {
    public static final long serialVersionUID = 1L;
}
