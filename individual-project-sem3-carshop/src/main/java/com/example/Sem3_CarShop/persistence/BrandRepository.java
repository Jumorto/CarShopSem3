package com.example.Sem3_CarShop.persistence;

import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
