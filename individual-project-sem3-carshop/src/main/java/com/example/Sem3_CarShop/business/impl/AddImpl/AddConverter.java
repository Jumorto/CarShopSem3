package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.Add;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;

public class AddConverter {
    private AddConverter (){

    }

    public static Add convert(AddEntity addEntity){
        return Add.builder()
                .id(addEntity.getId())
                .idUserCreate(addEntity.getIdUserCreate())
                .name(addEntity.getName())
                .description(addEntity.getDescription())
                .vehicleType(addEntity.getVehicleType())
                .brand(addEntity.getBrand())
                .engine_type(addEntity.getEngine_type())
                .gearbox(addEntity.getGearbox())
                .num_doors(addEntity.getNum_doors())
                .date_manufacture(addEntity.getDate_manufacture())
                .kilometers(addEntity.getKilometers())
                .price(addEntity.getPrice())
                .date_publish(addEntity.getDate_publish())
                .build();
    }
}
