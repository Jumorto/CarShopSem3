package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.AdvertisementSpecial;
import java.util.Date;

public class AdvertisementConverterSpecial {
    private AdvertisementConverterSpecial (){

    }

    public static AdvertisementSpecial convert(Object[] addEntitySp) {
        return AdvertisementSpecial.builder()
                .id((Long)addEntitySp[0])
                .idUserCreate((Long)addEntitySp[1])
                .name((String)addEntitySp[2])
                .description((String)addEntitySp[3])
                .vehicleType((String)addEntitySp[4])
                .brand((String)addEntitySp[5])
                .engine_type((String)addEntitySp[6])
                .gearbox((String)addEntitySp[7])
                .num_doors((Integer) addEntitySp[8])
                .date_manufacture((Date) addEntitySp[9])
                .kilometers((Long) addEntitySp[10])
                .price((Double) addEntitySp[11])
                .date_publish((Date) addEntitySp[12])
                .idVehicleType((Long)addEntitySp[13])
                .idBrand((Long)addEntitySp[14])
                .idEngineType((Long)addEntitySp[15])
                .idGearbox((Long)addEntitySp[16])
                .build();
    }
}
