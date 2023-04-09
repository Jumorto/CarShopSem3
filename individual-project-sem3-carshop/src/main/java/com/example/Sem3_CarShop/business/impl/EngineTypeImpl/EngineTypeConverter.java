package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.domain.EngineTypeDomain.EngineType;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;

public class EngineTypeConverter {
    private EngineTypeConverter(){

    }

    public static EngineType convert(EngineTypeEntity engineTypeEntity){
        return EngineType.builder()
                .id(engineTypeEntity.getId())
                .engineType(engineTypeEntity.getEngineType())
                .build();
    }
}
