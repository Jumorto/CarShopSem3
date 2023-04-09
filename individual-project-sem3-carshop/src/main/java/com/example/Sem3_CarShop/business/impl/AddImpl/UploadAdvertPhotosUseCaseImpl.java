package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.UploadAdvertPhotosUseCase;
import com.example.Sem3_CarShop.domain.AddDomain.UploadAdvertPhotosRequest;
import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import com.example.Sem3_CarShop.persistence.entity.AdvertPhotosEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class UploadAdvertPhotosUseCaseImpl implements UploadAdvertPhotosUseCase {

    private final AdvertPhotosRepository repository;

    @Override
    public void uploadPhotos(UploadAdvertPhotosRequest request, Long advertId){
        if(request.getPhotos().length > 0) {
            for (String photoStr : request.getPhotos()) {

                    String str[] = photoStr.split(","); // I remove the beginning of the string "data:image/jpeg;base64,"
                    byte[] decodedBytes = Base64.getDecoder().decode(str[1]);
                    AdvertPhotosEntity entity = AdvertPhotosEntity.builder().id_advert(advertId).photo(decodedBytes).build();
                    repository.save(entity);
            }
        }

    }
}
