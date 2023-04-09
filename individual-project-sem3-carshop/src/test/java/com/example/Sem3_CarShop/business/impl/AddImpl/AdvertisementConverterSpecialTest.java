package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.AdvertisementSpecial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AdvertisementConverterSpecialTest {

    @Test
    void convertObjectToAdvertisementSpecial() {

        Object[] addEntitySpMock = new Object[17];

        addEntitySpMock[0] = 1L;
        addEntitySpMock[1] = 1L;
        addEntitySpMock[2] = "Test Add";
        addEntitySpMock[3] = "ALAbala";
        addEntitySpMock[4] = "car";
        addEntitySpMock[5] = "Ferrari";
        addEntitySpMock[6] = "Gas";
        addEntitySpMock[7] = "Automatic";
        addEntitySpMock[8] = 3;
        addEntitySpMock[9] = null;
        addEntitySpMock[10] = 150000L;
        addEntitySpMock[11] = 15000.8;
        addEntitySpMock[12] = null;
        addEntitySpMock[13] = 1L;
        addEntitySpMock[14] = 1L;
        addEntitySpMock[15] = 1L;
        addEntitySpMock[16] = 1L;


        AdvertisementSpecial result = AdvertisementConverterSpecial.convert(addEntitySpMock);

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdUserCreate());
        assertEquals("Test Add", result.getName());
        assertEquals("ALAbala", result.getDescription());
        assertEquals("car", result.getVehicleType());
        assertEquals("Ferrari", result.getBrand());
        assertEquals("Gas", result.getEngine_type());
        assertEquals("Automatic", result.getGearbox());
        assertEquals(3, result.getNum_doors());
        assertEquals(null, result.getDate_manufacture());
        assertEquals(150000L, result.getKilometers());
        assertEquals(15000.8, result.getPrice());
        assertEquals(null, result.getDate_publish());
        assertEquals(1L, result.getIdVehicleType());
        assertEquals(1L, result.getIdBrand());
        assertEquals(1L, result.getIdEngineType());
        assertEquals(1L, result.getIdGearbox());

    }
}