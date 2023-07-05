package com.midox.MIDOX.inventory.service;

import com.midox.MIDOX.inventory.entity.GenericOptions;
import com.midox.MIDOX.inventory.repository.GenricRepository;
import com.midox.MIDOX.inventory.util.ConfigConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenericServiceImpl implements IGenericService {
    @Autowired
    private GenricRepository repo;

    private ConfigConstants constants;

    @Override
    public Integer addDropdownValue(GenericOptions genericOptions) {
        return repo.save(genericOptions).getDropId();
    }

    @Override
    public List<List<String>> getAllDropdowns() {
        List<GenericOptions> optionsList = repo.findAll().stream().sorted().collect(Collectors.toList());
        List<String> subCategoryList = new ArrayList<>();
        List<String> materialList = new ArrayList<>();
        List<String> colorFabricCodeList = new ArrayList<>();
        List<String> brandList = new ArrayList<>();
        List<String> productList = new ArrayList<>();
        List<String> designList = new ArrayList<>();


        optionsList.forEach(genericOptions -> {
            switch (genericOptions.getDropType()) {
                case ConfigConstants.DropDownTypes.BRAND:
                    brandList.add(genericOptions.getDropValue());
                case ConfigConstants.DropDownTypes.COLOR_FABRIC_CODE:
                    colorFabricCodeList.add(genericOptions.getDropValue());
                case ConfigConstants.DropDownTypes.DESIGN:
                    designList.add(genericOptions.getDropValue());
                case ConfigConstants.DropDownTypes.MATERIAL:
                    materialList.add(genericOptions.getDropValue());
                case ConfigConstants.DropDownTypes.PRODUCT:
                    productList.add(genericOptions.getDropValue());
                case ConfigConstants.DropDownTypes.SUB_CATEGORY:
                    subCategoryList.add(genericOptions.getDropValue());
            }
        });
        List<List<String>> dropdownsList = new ArrayList<>();
        return dropdownsList;
    }


}
