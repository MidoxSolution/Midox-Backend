package com.midox.MIDOX.inventory.service.api;

import com.midox.MIDOX.inventory.Exception.MidoxException;
import com.midox.MIDOX.inventory.constants.BusinessConstants;
import com.midox.MIDOX.inventory.entity.*;
import com.midox.MIDOX.inventory.models.RequestModels.StockHistorySearchCriteria;
import com.midox.MIDOX.inventory.repository.Adda.AddaMaterialRepository;
import com.midox.MIDOX.inventory.repository.Adda.AddaPatternRepository;
import com.midox.MIDOX.inventory.repository.Adda.AddaRepository;
import com.midox.MIDOX.inventory.service.spi.IAddaService;
import com.midox.MIDOX.inventory.service.spi.IStockHistoryService;
import com.midox.MIDOX.inventory.service.spi.IStockService;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddaServiceImpl implements IAddaService {

    @Autowired
    AddaRepository addaRepository;

    @Autowired
    AddaMaterialRepository addaMaterialRepo;

    @Autowired
    AddaPatternRepository addaPatternRepo;

    @Autowired
    IStockHistoryService stockHistoryService;

    @Autowired
    IStockService stockService;


    @Override
    public Adda addAdda(Adda adda) {
        adda.setDefaultValues();
        return addaRepository.saveAndFlush(adda);
    }

    @Override
    public Adda editAdda(Adda adda) throws MidoxException{
        if(null == adda.getAddaId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        adda.setDefaultValues();
        return addaRepository.saveAndFlush(adda);
    }

    @Override
    public AddaMaterial addAddaMaterial(AddaMaterial addaMaterial) throws MidoxException{
        createStockHistoryForAdda(addaMaterial);
        addaMaterial.setDefaultValues();
        return addaMaterialRepo.saveAndFlush(addaMaterial);
    }

    @Override
    public AddaMaterial updateAddaMaterial(AddaMaterial addaMaterial) throws MidoxException {
        //TODO put a check for bundle creation - no update allowed post bundle creation
        if(null == addaMaterial.getAddaMaterialId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        Optional<AddaMaterial> existingMaterial = addaMaterialRepo.findById(addaMaterial.getAddaMaterialId());
        if(existingMaterial.isEmpty()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        AddaMaterial existing = existingMaterial.get();
        updatedStockHistoryForAdda(addaMaterial);
        existing.setQuantity(addaMaterial.getQuantity());
        existing.setDefaultValues();
        return addaMaterialRepo.saveAndFlush(existing);
    }

    @Override
    public void deleteAddaMaterial(AddaMaterial addaMaterial) throws MidoxException{
        //TODO put a check for bundle creation - no update allowed post bundle creation
        if(null == addaMaterial.getAddaMaterialId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        deleteStockHistoryForAdda(addaMaterial);
        addaMaterialRepo.deleteById(addaMaterial.getAddaMaterialId());
    }

    @Override
    public AddaPattern addAddaPattern(AddaPattern addaPattern) {
        // TODO create bundle
        // TODO quantity check on total pattern and adda quantity
        addaPattern.setDefaultValues();
        return addaPatternRepo.saveAndFlush(addaPattern);
    }

    @Override
    public AddaPattern updateAddaPattern(AddaPattern addaPattern) throws MidoxException {
        if(null == addaPattern.getPatternId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        addaPattern.setDefaultValues();
        return addaPatternRepo.saveAndFlush(addaPattern);
    }

    @Override
    public void deleteAddaPattern(AddaPattern addaPattern) throws MidoxException{
        if(null == addaPattern.getPatternId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        addaPatternRepo.deleteById(addaPattern.getPatternId());
    }

    @Override
    public List<Adda> getAddaByCriteria(Integer designId, String designNo, Integer addaId, Integer brandID, String productCd, String addaNo) {
        List<Adda> addas =  addaRepository.findAddasByCriteria(designId, designNo, addaId, brandID, productCd, addaNo);
        addas.forEach(adda -> {
            adda.setAddaMaterials(addaMaterialRepo.findAllByAddaId(adda.getAddaId()));
            adda.setAddaPatterns(addaPatternRepo.findAllByAddaId(adda.getAddaId()));
        });
        return addas;
    }

    private void createStockHistoryForAdda(AddaMaterial addaMaterial) throws MidoxException{
        StockHistory history = new StockHistory();
        history.setStockId(addaMaterial.getStockId());
        history.setCreditDebitInd(BusinessConstants.DEBIT_INDICATOR);
        history.setQuantity(addaMaterial.getQuantity());
        history.setAddaId(addaMaterial.getAddaId());

        stockHistoryService.createStockHistory(history);
        Stock found = stockService.getStockById(addaMaterial.getStockId());
        found.setAvailableQuantity(found.getAvailableQuantity() - addaMaterial.getQuantity());
        stockService.updateStock(found);
    }

    private void updatedStockHistoryForAdda(AddaMaterial addaMaterial) throws MidoxException{
        StockHistorySearchCriteria searchCriteria = new StockHistorySearchCriteria();
        searchCriteria.setStockId(addaMaterial.getStockId());
        searchCriteria.setAddaId(addaMaterial.getAddaId());

        List<StockHistory> histories = stockHistoryService.getStockHistoryFromCriteria(searchCriteria);
        StockHistory history = histories.get(0);
        Stock found = stockService.getStockById(addaMaterial.getStockId());
        found.setAvailableQuantity(found.getAvailableQuantity() + history.getQuantity());
        history.setQuantity(addaMaterial.getQuantity());
        stockHistoryService.updateStockHistory(history);
        found.setAvailableQuantity(found.getAvailableQuantity() - history.getQuantity());
        stockService.updateStock(found);
    }

    private void deleteStockHistoryForAdda(AddaMaterial addaMaterial) throws MidoxException{
        Optional<AddaMaterial> material = addaMaterialRepo.findById(addaMaterial.getAddaMaterialId());
        if(material.isEmpty()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        addaMaterial =material.get();
        StockHistorySearchCriteria searchCriteria = new StockHistorySearchCriteria();
        searchCriteria.setStockId(addaMaterial.getStockId());
        searchCriteria.setAddaId(addaMaterial.getAddaId());

        List<StockHistory> histories = stockHistoryService.getStockHistoryFromCriteria(searchCriteria);
        if(!ValidationUtil.isEmpty(histories)) {
            StockHistory history = histories.get(0);
            Stock found = stockService.getStockById(addaMaterial.getStockId());
            found.setAvailableQuantity(found.getAvailableQuantity() + history.getQuantity());
            stockHistoryService.deleteStockHistory(history);
            stockService.updateStock(found);
        }
    }
}
