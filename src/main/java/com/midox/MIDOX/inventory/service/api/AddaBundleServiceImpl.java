package com.midox.MIDOX.inventory.service.api;

import com.midox.MIDOX.inventory.Exception.MidoxException;
import com.midox.MIDOX.inventory.constants.TextileEnum;
import com.midox.MIDOX.inventory.entity.*;
import com.midox.MIDOX.inventory.models.RequestModels.AddaBundleSearchCriteria;
import com.midox.MIDOX.inventory.models.RequestModels.AddaSearchCriteria;
import com.midox.MIDOX.inventory.repository.Adda.*;
import com.midox.MIDOX.inventory.service.spi.*;
import com.midox.MIDOX.inventory.util.MapperUtils;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddaBundleServiceImpl implements IAddaBundleService {

    @Autowired
    AddaBundleRepository bundleRepository;

    @Autowired
    AddaBundleHistoryRepository bundleHistoryRepo;

    @Autowired
    IAddaService addaService;

    @Override
    public AddaBundle createBundle(AddaBundle bundle) {
        bundle.setDefaultValues();
        return bundleRepository.saveAndFlush(bundle);
    }

    @Override
    public boolean createBundlesForPattern(AddaPattern pattern) {
        DesignProcess process = getDesignProcessesSorted(pattern.getAddaId()).get(0);
        String bundleNamePrefix = getBundleNamePrefix(pattern);
        Double totalQuantity = pattern.getQuantity();
        Integer bundleSize = pattern.getBundleSize();
        Integer totalBundles= (int) Math.ceil(totalQuantity/bundleSize);
        Double remainingQuantity = totalQuantity;
        for(int i =0; i< totalBundles; i++){
            AddaBundle bundle = new AddaBundle();
            bundle.setPatternId(pattern.getPatternId());
            bundle.setQuantity(remainingQuantity > Double.valueOf(bundleSize) ? Double.valueOf(bundleSize) : remainingQuantity);
            bundle.setCurrentProcessCd(process.getProcessCd());
            bundle.setCurrentProcessStatus(TextileEnum.ProcStatus.PROC_STAT_TBS);
            bundle.setStatus(TextileEnum.ProcStatus.PROC_STAT_TBS);
            bundle.setBundleName(bundleNamePrefix+i);
            bundle = createBundle(bundle);
            createBundleHistoryForBundle(bundle);
            remainingQuantity = remainingQuantity - bundleSize;
        }
        return true;
    }

    private List<DesignProcess> getDesignProcessesSorted(Integer addaId){
        List<DesignProcess> processes = addaService.getDesignProcessesForAdda(addaId);
        processes.sort((p1, p2) -> p1.getPriority().compareTo(p2.getPriority()));
        return processes;
    }

    private String getBundleNamePrefix(AddaPattern pattern){
        StringBuilder bundleName = new StringBuilder("A");
        bundleName.append(pattern.getAddaId()).append("_");
        bundleName.append(MapperUtils.getDisplayValueForEntity(pattern.getSize())).append("_");
        bundleName.append(MapperUtils.getDisplayValueForEntity(pattern.getColor())).append("_");
        bundleName.append("B");
        return bundleName.toString();
    }

    private void createBundleHistoryForBundle(AddaBundle bundle){
        AddaBundleHistory history = new AddaBundleHistory();
        history.setBundleId(bundle.getBundleId());
        history.setProcessCd(bundle.getCurrentProcessCd());
        history.setProcessStatus(bundle.getCurrentProcessStatus());
        history.setEmployeeId(bundle.getCurrentEmployeeId());
        history.setQuantity(bundle.getQuantity());
        createBundleHistory(history);

    }
    @Override
    public AddaBundle updateBundle(AddaBundle bundle) throws MidoxException {
        if(null == bundle.getBundleId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        bundle.setDefaultValues();
        return bundleRepository.saveAndFlush(bundle);
    }

    @Override
    public AddaBundle assignBundleToEmployee(Integer bundleId, Integer employeeId) throws MidoxException {
        AddaBundle bundle = bundleRepository.findById(bundleId).get();
        AddaBundleHistory history = getLatestBundleHistory(bundle);
        bundle.setCurrentEmployeeId(employeeId);
        bundle.setCurrentProcessStatus(TextileEnum.ProcStatus.PROC_STAT_INP);
        bundle.setStatus(TextileEnum.ProcStatus.PROC_STAT_INP);
        history.setEmployeeId(employeeId);
        history.setProcessStatus(TextileEnum.ProcStatus.PROC_STAT_INP);
        updateBundle(bundle);
        updateBundleHistory(history);
        return  bundle;
    }

    private AddaBundleHistory getLatestBundleHistory(AddaBundle bundle){
        AddaBundleSearchCriteria searchCriteria = new AddaBundleSearchCriteria();
        searchCriteria.setBundleId(bundle.getBundleId());
        searchCriteria.setProcessCd(bundle.getCurrentProcessCd());
        List<AddaBundleHistory> histories = bundleHistoryRepo.getBundleHistoryBySearchCriteria(bundle.getBundleId(), bundle.getCurrentProcessCd());
        // TODO check and handling for empty history
        return histories.get(0);
    }

    @Override
    public AddaBundle updateBundleStatus(Integer bundleId, TextileEnum.ProcStatus processStatus) throws MidoxException {
        AddaBundle bundle = bundleRepository.findById(bundleId).get();
        AddaBundleHistory history = getLatestBundleHistory(bundle);
        history.setProcessStatus(processStatus);
        bundle.setCurrentProcessStatus(processStatus);
        if(TextileEnum.ProcStatus.PROC_STAT_FIN.equals(processStatus)){
            history.setEndDate(new Date(System.currentTimeMillis()));
            AddaPattern pattern = addaService.getPatternById(bundle.getPatternId());
            List<DesignProcess> processes = getDesignProcessesSorted(pattern.getAddaId());
            Iterator<DesignProcess> it = processes.iterator();
            while(it.hasNext()){
                DesignProcess process = it.next();
                if(process.getProcessCd().equals(bundle.getCurrentProcessCd())){
                    it.remove();
                    break;
                }
                it.remove();
            }
            if(ValidationUtil.isEmpty(processes)){
                bundle = completeAddaBundle(bundle, pattern);
            }else{
                // create new bundle history for next process
                DesignProcess nextProcess = processes.get(0);
                bundle.setCurrentProcessCd(nextProcess.getProcessCd());
                bundle.setCurrentProcessStatus(TextileEnum.ProcStatus.PROC_STAT_TBS);
                bundle.setCurrentEmployeeId(null);
                // Create new bundle history
                createBundleHistoryForBundle(bundle);
            }
        }

        updateBundleHistory(history);

        updateBundle(bundle);

        return bundle;
    }

    private AddaBundle completeAddaBundle(AddaBundle bundle, AddaPattern pattern) throws MidoxException{
        // All process in design is completed for bundle. Mark it complete and update corresponding finished quantity
        bundle.setStatus(TextileEnum.ProcStatus.PROC_STAT_FIN);
        // TODO check if this shouldn't be set to null
        bundle.setCurrentProcessCd(null);
        bundle.setCurrentProcessStatus(null);
        bundle.setCurrentEmployeeId(null);
        // update pattern finished quantity also
        pattern.setFinishedQuantity(pattern.getFinishedQuantity() + bundle.getQuantity());
        addaService.updateAddaPattern(pattern);
        AddaSearchCriteria searchCriteria = new AddaSearchCriteria();
        searchCriteria.setAddaId(pattern.getAddaId());
        List<Adda> addas = addaService.getAddaByCriteria(searchCriteria);
        if(ValidationUtil.isNotEmpty(addas)){
            Adda adda = addas.get(0);
            adda.setFinishedQuantity(adda.getFinishedQuantity() + bundle.getQuantity());
            addaService.updateAdda(adda);
        }
        return bundle;
    }

    @Override
    public AddaBundleHistory createBundleHistory(AddaBundleHistory bundleHistory) {
        bundleHistory.setDefaultValues();
        return bundleHistoryRepo.saveAndFlush(bundleHistory);
    }

    @Override
    public AddaBundleHistory updateBundleHistory(AddaBundleHistory bundleHistory) throws MidoxException {
        if(null == bundleHistory.getBundleHistoryId()){
            throw new MidoxException(MidoxException.EXCEPTION_ENTITY_DOES_NOT_EXIST);
        }
        bundleHistory.setDefaultValues();
        return bundleHistoryRepo.saveAndFlush(bundleHistory);
    }

    @Override
    public void deleteAddaBundle(AddaBundle bundle) throws MidoxException {
        // TODO to be implemented. consider adding new bundle manually also.
    }

    @Override
    public List<AddaBundle> getAddaBundleByCriteria(AddaBundleSearchCriteria searchCriteria) {
        return null;
    }
}
