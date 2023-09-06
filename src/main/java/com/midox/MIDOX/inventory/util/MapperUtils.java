package com.midox.MIDOX.inventory.util;

import com.midox.MIDOX.MidoxApplication;
import com.midox.MIDOX.inventory.entity.GroupEntity;
import com.midox.MIDOX.inventory.service.spi.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MapperUtils {

    /*IGroupService groupService;

    @Autowired
    public MapperUtils(IGroupService groupService){
        this.groupService = groupService;
    }*/

    public static Map getallEntitiesMap(){
        return MidoxApplication.getAppContext().getBean(IGroupService.class).getEntitiesMap();
    }

    public static String getDisplayValueForEntity(String entityCd){
        GroupEntity entity =  MidoxApplication.getAppContext().getBean(IGroupService.class).getEntitiesMap().get(entityCd);
        return entity.getDisplayValue();
    }
}
