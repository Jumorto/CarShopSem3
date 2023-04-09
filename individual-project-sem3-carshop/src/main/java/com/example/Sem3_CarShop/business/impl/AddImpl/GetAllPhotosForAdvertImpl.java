package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.GetAllPhotosByAdvertID;
import com.example.Sem3_CarShop.domain.AddDomain.AdvertPhoto;
import com.example.Sem3_CarShop.domain.AddDomain.GetPhotosByAdvertIDResponse;
import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPhotosForAdvertImpl implements GetAllPhotosByAdvertID {
    private final AdvertPhotosRepository repository;

    @Override
    public GetPhotosByAdvertIDResponse getPhotosByIdAdvert(Long idAdvert){
        List<Object[]> results = repository.findAllById_advert(idAdvert);
        List<AdvertPhoto> photos = results.stream().map(AdvertPhotoConverter::convert).toList();
        GetPhotosByAdvertIDResponse response = GetPhotosByAdvertIDResponse.builder().advertPhotoList(photos).build();
        return response;
    }
}
