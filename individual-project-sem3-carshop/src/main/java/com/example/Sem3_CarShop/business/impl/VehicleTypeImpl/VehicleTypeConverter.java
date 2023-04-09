package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.domain.VehicleTypeDomain.VehicleType;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;

public class VehicleTypeConverter {

    private VehicleTypeConverter(){

    }

    public static VehicleType convert(VehicleTypeEntity vehicleTypeEntity){
        return VehicleType.builder()
                .id(vehicleTypeEntity.getId())
                .vehicleType(vehicleTypeEntity.getVehicleType())
                .build();
    }
}
