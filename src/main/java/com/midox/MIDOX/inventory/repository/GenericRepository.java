package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.GenericOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository extends JpaRepository<GenericOptions, Integer> {
}
