package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.AdvertPhoto;
import com.example.Sem3_CarShop.domain.AddDomain.GetPhotosByAdvertIDResponse;
import com.example.Sem3_CarShop.persistence.AdvertPhotosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPhotosForAdvertImplTest {

    @Mock
    private AdvertPhotosRepository repositoryMock;

    @InjectMocks
    public GetAllPhotosForAdvertImpl getAllPhotosForAdvert;

    @Test
    void testGetPhotosByIdAdvert() {
        long idAdvert = 1;
        List<Object[]> mockResult = new ArrayList<>();
        Object[] result1 = { 1L, 1L, "data1".getBytes()};
        Object[] result2 = { 2L, 1L, "data2".getBytes()};
        mockResult.add(result1);
        mockResult.add(result2);

        when(repositoryMock.findAllById_advert(anyLong())).thenReturn(mockResult);

        GetPhotosByAdvertIDResponse response = getAllPhotosForAdvert.getPhotosByIdAdvert(idAdvert);
        List<AdvertPhoto> photos = response.getAdvertPhotoList();

        assertEquals(2, photos.size());
        assertEquals(1L, photos.get(0).getId());
        assertEquals(1L, photos.get(0).getId_advert());
        assertEquals("data:image/jpeg;base64," + Base64.getEncoder().encodeToString("data1".getBytes()), photos.get(0).getPhoto());
        assertEquals(2L, photos.get(1).getId());
        assertEquals(1L, photos.get(1).getId_advert());
        assertEquals("data:image/jpeg;base64," + Base64.getEncoder().encodeToString("data2".getBytes()), photos.get(1).getPhoto());
    }
}