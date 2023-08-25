package com.midox.MIDOX.inventory.util.Mapper;

import com.midox.MIDOX.inventory.entity.Design;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import com.midox.MIDOX.inventory.models.DesignProcessResponse;
import com.midox.MIDOX.inventory.models.DesignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {EntityCodeMapper.class, DesignProcessMapper.class})
public interface DesignMapper {

    public abstract DesignResponse toDesignResponse(Design design);

    List<DesignProcessResponse> getDesignProcesses(List<DesignProcess> processes);

}
