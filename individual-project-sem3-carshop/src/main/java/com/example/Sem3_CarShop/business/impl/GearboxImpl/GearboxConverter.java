package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.domain.GearboxDomain.Gearbox;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;

public class GearboxConverter {
    private GearboxConverter(){

    }

    public static Gearbox convert(GearboxEntity gearboxEntity){
        return Gearbox.builder()
                .id(gearboxEntity.getId())
                .gearboxType(gearboxEntity.getGearboxType())
                .build();
    }
}
