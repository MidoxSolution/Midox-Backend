package com.midox.MIDOX.inventory.util.Mapper;

import com.midox.MIDOX.inventory.constants.TextileEnum;
import com.midox.MIDOX.inventory.entity.Design;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import com.midox.MIDOX.inventory.entity.Stock;
import com.midox.MIDOX.inventory.models.DesginResponse;
import com.midox.MIDOX.inventory.models.EntityCode;
import com.midox.MIDOX.inventory.models.StockResponse;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {EntityCodeMapper.class, DesginProcessMapper.class})
public abstract class DesignMapper {

    public abstract DesginResponse toDesignResponse(Design design);


}
