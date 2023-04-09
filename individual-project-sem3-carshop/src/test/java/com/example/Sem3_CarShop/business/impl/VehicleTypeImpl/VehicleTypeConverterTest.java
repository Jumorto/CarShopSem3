package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;


import com.example.Sem3_CarShop.domain.VehicleTypeDomain.VehicleType;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeConverterTest {

    @Test
    void convert() {
        VehicleTypeEntity vehicleEntityMock = Mockito.mock(VehicleTypeEntity.class);

        Mockito.when(vehicleEntityMock.getId()).thenReturn(1L);
        Mockito.when(vehicleEntityMock.getVehicleType()).thenReturn("Test VehicleType");

        VehicleType result = VehicleTypeConverter.convert(vehicleEntityMock);

        Mockito.verify(vehicleEntityMock).getId();
        Mockito.verify(vehicleEntityMock).getVehicleType();

        assertEquals(1L, result.getId());
        assertEquals("Test VehicleType", result.getVehicleType());
    }
}