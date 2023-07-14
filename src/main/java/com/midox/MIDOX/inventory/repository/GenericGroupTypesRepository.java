package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.GenericGroupTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericGroupTypesRepository extends JpaRepository<GenericGroupTypes, Integer> {
}
