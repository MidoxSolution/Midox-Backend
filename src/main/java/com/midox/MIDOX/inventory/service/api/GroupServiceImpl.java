package com.midox.MIDOX.inventory.service.api;

import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.entity.GroupMaster;
import com.midox.MIDOX.inventory.entity.GroupEntity;
import com.midox.MIDOX.inventory.repository.GroupEntityRepository;
import com.midox.MIDOX.inventory.repository.GroupMasterRepository;
import com.midox.MIDOX.inventory.service.spi.IGroupService;
import com.midox.MIDOX.inventory.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {
    private final GroupEntityRepository groupEntityRepo;
    private final GroupMasterRepository groupMasterRepo;

    private ConfigConstants constants;

    @Override
    public Boolean createGroupEntities(List<GroupEntity> groupEntities) {
        groupEntities.forEach(entity -> {
            entity.setDefaultValues();
            groupEntityRepo.saveAndFlush(entity);
        });
        return true;
    }

    @Override
    public Boolean createGroupMaster(GroupMaster groupMaster){
        groupMaster.setDefaultValues();
        groupMasterRepo.saveAndFlush(groupMaster);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGroupEntity(String entityCd){
        groupEntityRepo.deleteGroupEntityByEntityCd(entityCd);
    }

    @Override
    public Boolean editGroupEntity(GroupEntity groupEntity){
        GroupEntity existingEntity = groupEntityRepo.findGroupEntityByEntityCd(groupEntity.getEntityCd());
        existingEntity.setDisplayValue(groupEntity.getDisplayValue());
        existingEntity.setParentEntityCd(groupEntity.getParentEntityCd());
        // TODO write explicit update call and update audit details before save. The employee session will be used for getting updated_by.
        groupEntityRepo.save(existingEntity);
        return true;
    }

    @Override
    public Map<GroupMaster, List<GroupEntity>> getAllGroupWithEntities() {
        List<GroupEntity> optionsList = groupEntityRepo.findAll();
        if (ValidationUtil.isNotNull(optionsList)) {
            //return optionsList.stream().distinct().collect(Collectors.groupingBy(GroupEntity::getGenericGroupType));
            return null;
        } else {
            return Collections.EMPTY_MAP;
        }

    }

    @Override
    public List<GroupEntity> getAllEntitiesForGroupMaster(String groupMasterCd){
        List<GroupEntity> entities = groupEntityRepo.findAllForGroupMasterCd(groupMasterCd);
        return entities;
    }

// would be better to keep these also configurable - this would be part of the drop_type thins I mentioned in Generic Options
}
