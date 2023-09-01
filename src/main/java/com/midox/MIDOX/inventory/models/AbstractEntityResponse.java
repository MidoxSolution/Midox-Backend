package com.midox.MIDOX.inventory.models;

import com.midox.MIDOX.inventory.entity.AbstractDataEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class AbstractEntityResponse implements ResponseWrapper{

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private EmployeeDetails createdBy;

    private EmployeeDetails updatedBy;
}
