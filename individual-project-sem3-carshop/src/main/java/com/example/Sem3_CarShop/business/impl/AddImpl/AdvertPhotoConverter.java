package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.Add;
import com.example.Sem3_CarShop.domain.AddDomain.AdvertPhoto;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;

import java.util.Base64;

public class AdvertPhotoConverter {
    private AdvertPhotoConverter (){

    }

    public static AdvertPhoto convert(Object[] advPhotoEntity){
        return AdvertPhoto.builder()
                .id((Long)advPhotoEntity[0])
                .id_advert((Long)advPhotoEntity[1])
                .photo("data:image/jpeg;base64," + Base64.getEncoder().encodeToString((byte[])advPhotoEntity[2]))
                .build();
    }
}
