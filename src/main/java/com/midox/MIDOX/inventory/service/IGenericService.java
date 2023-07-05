package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.GenericOptions;

import java.util.List;

public interface IGenericService {

    public Integer addDropdownValue(GenericOptions genericOptions);

    public List<List<String>> getAllDropdowns();

}
