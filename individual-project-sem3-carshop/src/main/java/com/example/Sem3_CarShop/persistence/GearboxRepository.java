package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearboxRepository extends JpaRepository<GearboxEntity, Long> {
}
