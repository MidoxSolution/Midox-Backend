package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.GenericOptions;
import com.midox.MIDOX.inventory.repository.GenricRepository;
import com.midox.MIDOX.inventory.constants.ConfigConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenericServiceImpl implements IGenericService {
    @Autowired
    private GenricRepository repo;

    private ConfigConstants constants;

    @Override
    public Boolean addDropdownValue(List<GenericOptions> genericOptions) {
        genericOptions.forEach(genericOption -> repo.save(genericOption));
        return true;
    }

    @Override
    public Map<String, List<GenericOptions>> getAllDropdowns() {
        List<GenericOptions> optionsList = repo.findAll();
        return optionsList.stream().distinct().collect(Collectors.groupingBy(GenericOptions::getGenericGroupType));

    }

// would be better to keep these also configurable - this would be part of the drop_type thins I mentioned in Generic Options
}
