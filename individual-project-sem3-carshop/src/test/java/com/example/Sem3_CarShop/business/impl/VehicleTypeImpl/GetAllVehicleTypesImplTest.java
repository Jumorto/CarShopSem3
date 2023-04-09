package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.domain.VehicleTypeDomain.GetAllVehicleTypesResponse;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.VehicleType;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllVehicleTypesImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepositoryMock;

    @InjectMocks
    private GetAllVehicleTypesImpl getAllVehicleTypes;
    @Test
    void getAllVehicleTypes() {
        VehicleTypeEntity testVehicleType1 = VehicleTypeEntity.builder().id(1L).vehicleType("truck").build();
        VehicleTypeEntity testVehicleType2 = VehicleTypeEntity.builder().id(2L).vehicleType("car").build();
        VehicleTypeEntity testVehicleType3 = VehicleTypeEntity.builder().id(3L).vehicleType("bus").build();
        when(vehicleTypeRepositoryMock.findAll())
                .thenReturn(List.of(testVehicleType1, testVehicleType2, testVehicleType3));

        GetAllVehicleTypesResponse actual = getAllVehicleTypes.getAllVehicleTypes();

        VehicleType testVT1 = VehicleType.builder().id(1L).vehicleType("truck").build();
        VehicleType testVT2 = VehicleType.builder().id(2L).vehicleType("car").build();
        VehicleType testVT3 = VehicleType.builder().id(3L).vehicleType("bus").build();
        GetAllVehicleTypesResponse expected = GetAllVehicleTypesResponse.builder().vehicleTypes(List.of(testVT1, testVT2, testVT3)).build();

        assertEquals(expected, actual);
        verify(vehicleTypeRepositoryMock).findAll();
    }
}