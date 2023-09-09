package com.midox.MIDOX.inventory.repository.Adda;

import com.midox.MIDOX.inventory.entity.AddaBundle;
import com.midox.MIDOX.inventory.entity.AddaPattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddaBundleRepository extends JpaRepository<AddaBundle, Integer> {

    List<AddaBundle> findAllByPatternId(Integer patterId);
}
