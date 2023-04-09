package com.example.Sem3_CarShop.business.AddUseCases;

import com.example.Sem3_CarShop.domain.AddDomain.GetPhotosByAdvertIDResponse;

public interface GetAllPhotosByAdvertID {
    GetPhotosByAdvertIDResponse getPhotosByIdAdvert(Long idAdvert);
}
