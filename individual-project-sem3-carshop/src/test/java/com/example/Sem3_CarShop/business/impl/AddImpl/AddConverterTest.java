package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.domain.AddDomain.Add;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AddConverterTest {

    @Test
    void convertAddEntityToAdd() {
        // Create a mock instance of AddEntity
        AddEntity addEntityMock = Mockito.mock(AddEntity.class);

        // Set up the mock to return appropriate values for the getter methods
        Mockito.when(addEntityMock.getId()).thenReturn(1L);
        Mockito.when(addEntityMock.getIdUserCreate()).thenReturn(1L);
        Mockito.when(addEntityMock.getName()).thenReturn("Test Add");
        Mockito.when(addEntityMock.getDescription()).thenReturn("ALAbala");
        Mockito.when(addEntityMock.getVehicleType()).thenReturn(1);
        Mockito.when(addEntityMock.getBrand()).thenReturn(1);
        Mockito.when(addEntityMock.getEngine_type()).thenReturn(1);
        Mockito.when(addEntityMock.getGearbox()).thenReturn(1);
        Mockito.when(addEntityMock.getNum_doors()).thenReturn(3);
        Mockito.when(addEntityMock.getDate_manufacture()).thenReturn(null);
        Mockito.when(addEntityMock.getKilometers()).thenReturn(150000L);
        Mockito.when(addEntityMock.getPrice()).thenReturn(15000.8);
        Mockito.when(addEntityMock.getDate_publish()).thenReturn(null);

        // Call the convert method

        Add result = AddConverter.convert(addEntityMock);

        // Verify that the convert method correctly calls the getter methods on the AddEntity instance
        // and returns an Add object with the expected values
        Mockito.verify(addEntityMock).getId();
        Mockito.verify(addEntityMock).getIdUserCreate();
        Mockito.verify(addEntityMock).getName();
        Mockito.verify(addEntityMock).getDescription();
        Mockito.verify(addEntityMock).getVehicleType();
        Mockito.verify(addEntityMock).getBrand();
        Mockito.verify(addEntityMock).getEngine_type();
        Mockito.verify(addEntityMock).getGearbox();
        Mockito.verify(addEntityMock).getNum_doors();
        Mockito.verify(addEntityMock).getDate_manufacture();
        Mockito.verify(addEntityMock).getKilometers();
        Mockito.verify(addEntityMock).getPrice();
        Mockito.verify(addEntityMock).getDate_publish();
        // ... verify other getter methods as needed

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdUserCreate());
        assertEquals("Test Add", result.getName());
        assertEquals("ALAbala", result.getDescription());
        assertEquals(1, result.getVehicleType());
        assertEquals(1, result.getBrand());
        assertEquals(1, result.getEngine_type());
        assertEquals(1, result.getGearbox());
        assertEquals(3, result.getNum_doors());
        assertEquals(null, result.getDate_manufacture());
        assertEquals(150000L, result.getKilometers());
        assertEquals(15000.8, result.getPrice());
        assertEquals(null, result.getDate_publish());

    }
}