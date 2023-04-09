package com.example.Sem3_CarShop.business.impl.GearboxImpl;


import com.example.Sem3_CarShop.domain.GearboxDomain.Gearbox;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GearboxConverterTest {

    @Test
    void convert() {
        GearboxEntity gearboxEntityMock = Mockito.mock(GearboxEntity.class);

        Mockito.when(gearboxEntityMock.getId()).thenReturn(1L);
        Mockito.when(gearboxEntityMock.getGearboxType()).thenReturn("Test Gearbox");

        Gearbox result = GearboxConverter.convert(gearboxEntityMock);

        Mockito.verify(gearboxEntityMock).getId();
        Mockito.verify(gearboxEntityMock).getGearboxType();

        assertEquals(1L, result.getId());
        assertEquals("Test Gearbox", result.getGearboxType());
    }
}