package com.midox.MIDOX.inventory.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.sql.Timestamp;

@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
@Data
public class AbstractDataEntity implements Serializable, SetDefaultValues {
    public static final long serialVersionUID = 1L;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Override
    public void setDefaultValues(){
        this.createdBy = null == this.createdBy ? 1 : this.createdBy;
        this.createdAt = null == this.createdAt? new Timestamp(System.currentTimeMillis()) : this.createdAt;
        this.updatedBy = null == this.updatedBy? 1 : this.updatedBy;
        this.updatedAt = null == this.updatedAt? new Timestamp(System.currentTimeMillis()) : this.updatedAt;
    }
/*
    public Timestamp getCreatedAt() {
        return null == createdAt? new Timestamp(System.currentTimeMillis()) : createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return null == updatedAt? new Timestamp(System.currentTimeMillis()) : updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return null == createdBy? 1 : createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return null == updatedBy? 1 : updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }*/
}
