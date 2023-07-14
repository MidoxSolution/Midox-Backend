package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.GenericGroupTypes;
import com.midox.MIDOX.inventory.entity.GenericOptions;
import com.midox.MIDOX.inventory.repository.GenericRepository;
import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenericServiceImpl implements IGenericService {
    private final GenericRepository genericRepo;

    private  ConfigConstants constants;

    @Override
    public Boolean addDropdownValue(List<GenericOptions> genericOptions) {
        genericOptions.forEach(genericOption -> genericRepo.save(genericOption));
        return true;
    }

    @Override
    public Map<GenericGroupTypes, List<GenericOptions>> getAllDropdowns() {
        List<GenericOptions> optionsList = genericRepo.findAll();
        if(ValidationUtil.isEmpty(optionsList)) {
            return optionsList.stream().distinct().collect(Collectors.groupingBy(GenericOptions::getGenericGroupType));
        }
        return Collections.EMPTY_MAP;

    }

// would be better to keep these also configurable - this would be part of the drop_type thins I mentioned in Generic Options
}
