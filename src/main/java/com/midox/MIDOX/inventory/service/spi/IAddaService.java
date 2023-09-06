package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.Exception.MidoxException;
import com.midox.MIDOX.inventory.entity.Adda;
import com.midox.MIDOX.inventory.entity.AddaMaterial;
import com.midox.MIDOX.inventory.entity.AddaPattern;
import com.midox.MIDOX.inventory.entity.Design;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAddaService {
    @Transactional(propagation = Propagation.REQUIRED)
    Adda addAdda(Adda adda);

    @Transactional(propagation = Propagation.REQUIRED)
    Adda editAdda(Adda adda) throws MidoxException;

    AddaMaterial updateAddaMaterial(AddaMaterial addaMaterial) throws MidoxException;

    @Transactional(propagation = Propagation.REQUIRED)
    AddaMaterial addAddaMaterial(AddaMaterial addaMaterial) throws MidoxException;

    void deleteAddaMaterial(AddaMaterial addaMaterial) throws MidoxException;

    AddaPattern updateAddaPattern(AddaPattern addaPattern) throws MidoxException;

    @Transactional(propagation = Propagation.REQUIRED)
    AddaPattern addAddaPattern(AddaPattern addaPattern);

    void deleteAddaPattern(AddaPattern addaPattern) throws MidoxException;

     List<Adda> getAddaByCriteria(Integer designId, String designNo, Integer addaId, Integer brandID, String productCd, String addaNo);
}
