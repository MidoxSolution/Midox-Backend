package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.GenericOptions;

import java.util.List;
import java.util.Map;

public interface IGenericService {

    public Boolean addDropdownValue(List<GenericOptions> genericOptions);

    public Map<String, List<GenericOptions>> getAllDropdowns();

}
