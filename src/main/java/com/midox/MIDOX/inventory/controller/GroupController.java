package com.midox.MIDOX.inventory.controller;

import com.midox.MIDOX.inventory.entity.GroupMaster;
import com.midox.MIDOX.inventory.entity.GroupEntity;
import com.midox.MIDOX.inventory.service.spi.IGroupService;
import com.midox.MIDOX.inventory.constants.ConfigConstants;
import com.midox.MIDOX.inventory.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private IGroupService service;

    @GetMapping("/get-all-groups")
    public ResponseEntity<Map<GroupMaster, List<GroupEntity>>> getGroupEntities() {
        ResponseEntity<Map<GroupMaster, List<GroupEntity>>> response = null;
        Map<GroupMaster, List<GroupEntity>> dropdownsList = service.getAllGroupWithEntities();
        response = new ResponseEntity<>(dropdownsList, HttpStatus.OK);
        return response;
    }

    @PostMapping("/save-entity")
    public ResponseEntity<Message> addGroupEntities(@RequestBody List<GroupEntity> groupEntities) {
        ResponseEntity<Message> response = null;
        service.createGroupEntities(groupEntities);
        response = new ResponseEntity<>(new Message(ConfigConstants.Messages.GROUP_ENTITY_ADDED), HttpStatus.OK);
        return response;
    }

    @PostMapping("/save-master")
    public ResponseEntity<Message> addGroupMaster(@RequestBody GroupMaster groupMaster) {
        ResponseEntity<Message> response = null;
        service.createGroupMaster(groupMaster);
        response = new ResponseEntity<>(new Message(ConfigConstants.Messages.GROUP_MASTER_ADDED), HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete-entity/{entityCd}")
    public ResponseEntity<Message> deleteGroupEntities(@PathVariable String entityCd) {
        ResponseEntity<Message> response = null;
        service.deleteGroupEntity(entityCd);
        response = new ResponseEntity<>(new Message(ConfigConstants.Messages.GROUP_ENTITY_DELETED), HttpStatus.OK);
        return response;
    }

    @PostMapping("/edit-entity")
    public ResponseEntity<Message> editGroupEntity(@RequestBody GroupEntity groupEntity) {
        ResponseEntity<Message> response = null;
        service.editGroupEntity(groupEntity);
        response = new ResponseEntity<>(new Message(ConfigConstants.Messages.GROUP_ENTITY_EDITED), HttpStatus.OK);
        return response;
    }

    @GetMapping("/get-entities/{masterCd}")
    public ResponseEntity<?> getAllEntities(@PathVariable String masterCd) {
        List entities = service.getAllEntitiesForGroupMaster(masterCd);
        ResponseEntity response = new ResponseEntity<>(entities, HttpStatus.OK);
        return response;
    }


}
