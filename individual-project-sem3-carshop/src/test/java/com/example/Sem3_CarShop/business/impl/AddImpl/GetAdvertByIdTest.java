package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.exceptions.NotFoundAdvertisementExeption;
import com.example.Sem3_CarShop.domain.AddDomain.AdvertisementSpecial;
import com.example.Sem3_CarShop.domain.AddDomain.GetAdvertByIdResponse;
import com.example.Sem3_CarShop.persistence.AddRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAdvertByIdTest {

    @Mock
    private AddRepository addRepositoryMock;

    @InjectMocks
    public GetAdvertById getAdvertById;

    @Test
    void testGetAdvertById_validId() {
        long id = 1;
        List<Object[]> mockAdvert = new ArrayList<>();
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
        mockAdvert.add(addEntitySpMock);

        when(addRepositoryMock.findAdvertById(anyLong())).thenReturn(mockAdvert);

        GetAdvertByIdResponse response = getAdvertById.getAdvertById(id);
        AdvertisementSpecial result = response.getAdvert();

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

    @Test
    void testGetAdvertById_invalidId() {
        long id = 2;
        List<Object[]> mockAdvert = new ArrayList<>();

        when(addRepositoryMock.findAdvertById(anyLong())).thenReturn(mockAdvert);

        assertThrows(NotFoundAdvertisementExeption.class, () -> getAdvertById.getAdvertById(id));
    }
}