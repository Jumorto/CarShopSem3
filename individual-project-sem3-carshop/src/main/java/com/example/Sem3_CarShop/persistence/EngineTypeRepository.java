package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineTypeRepository extends JpaRepository<EngineTypeEntity, Long> {
}
