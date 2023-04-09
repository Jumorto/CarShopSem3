package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.domain.EngineTypeDomain.EngineType;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EngineTypeConverterTest {

    @Test
    void convert() {
        EngineTypeEntity engineTypeEntityMock = Mockito.mock(EngineTypeEntity.class);

        Mockito.when(engineTypeEntityMock.getId()).thenReturn(1L);
        Mockito.when(engineTypeEntityMock.getEngineType()).thenReturn("Test Engine Type");

        EngineType result = EngineTypeConverter.convert(engineTypeEntityMock);

        Mockito.verify(engineTypeEntityMock).getId();
        Mockito.verify(engineTypeEntityMock).getEngineType();

        assertEquals(1L, result.getId());
        assertEquals("Test Engine Type", result.getEngineType());
    }
}