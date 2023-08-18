package com.midox.MIDOX.inventory.service.spi;

import com.midox.MIDOX.inventory.entity.Brand;
import com.midox.MIDOX.inventory.entity.Design;
import com.midox.MIDOX.inventory.entity.DesignProcess;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDesignProcessService {
    @Transactional(propagation = Propagation.REQUIRED)
    void addDesignProcess(DesignProcess designProcess);

    void addDesignProcesses(List<DesignProcess> designProcesses);

   // Design addDesignWithProcess(Design design);

    @Transactional(propagation = Propagation.REQUIRED)
    Design updateDesignProcess(Design design);

    List<DesignProcess> getAllProcessesForDesgin(Integer desginId);

}
