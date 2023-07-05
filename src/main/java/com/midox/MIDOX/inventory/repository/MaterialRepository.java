package com.midox.MIDOX.inventory.repository;

import com.midox.MIDOX.inventory.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material,Integer> {
}
