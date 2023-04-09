package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.UploadAdvertPhotosRequest;
import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import com.example.Sem3_CarShop.persistence.entity.AdvertPhotosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UploadAdvertPhotosUseCaseImplTest {
    @Mock
    private AdvertPhotosRepository repositoryMock;

    @InjectMocks
    public UploadAdvertPhotosUseCaseImpl uploadAdvertPhotosUseCase;

    @Test
    void testUploadPhotos() {
        String[] photos = { "data:image/jpeg;base64,AQID", "data:image/jpeg;base64,AQID" };
        Long advertId = 1L;
        UploadAdvertPhotosRequest request = new UploadAdvertPhotosRequest(photos);

        uploadAdvertPhotosUseCase.uploadPhotos(request, advertId);

        verify(repositoryMock, times(photos.length)).save(any(AdvertPhotosEntity.class));
    }
}