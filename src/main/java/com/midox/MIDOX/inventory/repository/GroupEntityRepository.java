package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.GroupEntity;
import com.midox.MIDOX.inventory.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupEntityRepository extends JpaRepository<GroupEntity, Integer> {

    String findAllForGroupMasterCdQuery = "select * from group_entity where master_cd = :master_cd";

    String findGroupEntityByEntityCdQuery = "select * from group_entity where entity_cd = :entity_cd";

    String deleteGroupEntityByEntityCdQuery = "delete from group_entity where entity_cd = :entity_cd or parent_entity_cd = :entity_cd";


    @Query(value = findAllForGroupMasterCdQuery, nativeQuery = true)
    List<GroupEntity> findAllForGroupMasterCd(@Param("master_cd") String masterCd);

    @Modifying
    @Query(value = deleteGroupEntityByEntityCdQuery, nativeQuery = true)
    void deleteGroupEntityByEntityCd(@Param("entity_cd") String entityCd);

    @Query(value = findGroupEntityByEntityCdQuery, nativeQuery = true)
    GroupEntity findGroupEntityByEntityCd(@Param("entity_cd") String entityCd);
}
