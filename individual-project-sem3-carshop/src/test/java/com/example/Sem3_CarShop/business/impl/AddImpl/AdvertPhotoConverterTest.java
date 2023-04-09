package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.AdvertPhoto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AdvertPhotoConverterTest {

    @Test
    void testConvert() {
        long id = 1;
        long idAdvert = 2;
        byte[] imageData = "image data".getBytes();
        Object[] advPhotoEntity = {id, idAdvert, imageData};

        AdvertPhoto advertPhoto = AdvertPhotoConverter.convert(advPhotoEntity);

        assertEquals(id, advertPhoto.getId());
        assertEquals(idAdvert, advertPhoto.getId_advert());
        assertEquals("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData), advertPhoto.getPhoto());
    }
}