package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {
}
