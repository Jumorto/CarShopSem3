package com.example.Sem3_CarShop.business.impl.BrandImpl;

import com.example.Sem3_CarShop.domain.BrandDomain.Brand;
import com.example.Sem3_CarShop.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BrandConverterTest {

    @Test
    void convert() {
        BrandEntity brandEntityMock = Mockito.mock(BrandEntity.class);

        Mockito.when(brandEntityMock.getId()).thenReturn(1L);
        Mockito.when(brandEntityMock.getBrandName()).thenReturn("Test Brand");

        Brand result = BrandConverter.convert(brandEntityMock);

        Mockito.verify(brandEntityMock).getId();
        Mockito.verify(brandEntityMock).getBrandName();

        assertEquals(1L, result.getId());
        assertEquals("Test Brand", result.getBrandName());
    }
}