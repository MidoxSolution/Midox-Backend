package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.GroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMasterRepository extends JpaRepository<GroupMaster, Integer> {
}
