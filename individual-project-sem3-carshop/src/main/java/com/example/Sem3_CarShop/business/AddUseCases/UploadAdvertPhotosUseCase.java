package com.example.Sem3_CarShop.business.AddUseCases;

import com.example.Sem3_CarShop.domain.AddDomain.UploadAdvertPhotosRequest;

public interface UploadAdvertPhotosUseCase {
    void uploadPhotos(UploadAdvertPhotosRequest request, Long advertId);
}
