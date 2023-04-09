package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAllPhotosByAdvertIdImplTest {

    @Mock
    private AdvertPhotosRepository repositoryMock;

    @InjectMocks
    public DeleteAllPhotosByAdvertIdImpl deleteAllPhotosByAdvertId;

    @Test
    public void testDeletePhotosByAdvertId() {
        Long advertId = 1L;
        deleteAllPhotosByAdvertId.deletePhotosByAdvertId(advertId);
        verify(repositoryMock, times(1)).deleteAllById_advert(advertId);
    }
}