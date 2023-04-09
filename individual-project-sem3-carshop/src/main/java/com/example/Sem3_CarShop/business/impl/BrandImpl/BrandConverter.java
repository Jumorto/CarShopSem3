package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.domain.BrandDomain.Brand;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;

public class BrandConverter {
    private BrandConverter(){

    }

    public static Brand convert(BrandEntity brandEntity){
        return Brand.builder()
                .id(brandEntity.getId())
                .brandName(brandEntity.getBrandName())
                .build();
    }
}
