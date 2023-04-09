package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeRequest;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeResponse;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateVehicleTypeUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepositoryMock;

    @InjectMocks
    private CreateVehicleTypeUseCaseImpl createVehicleTypeUseCase;

    @Test
    void createNewVehicleType() {
        VehicleTypeEntity testVehicleType= VehicleTypeEntity.builder().id(null).vehicleType("truck").build();
        VehicleTypeEntity testVehicleType1 = VehicleTypeEntity.builder().id(1L).vehicleType("truck").build();
        when(vehicleTypeRepositoryMock.save(testVehicleType))/*.thenThrow(new RuntimeException())*/
                .thenReturn(testVehicleType1);

        CreateNewVehicleTypeRequest request = CreateNewVehicleTypeRequest.builder().vehicleType("truck").build();
        CreateNewVehicleTypeResponse actualResponse = createVehicleTypeUseCase.createNewVehicleType(request);

        assertEquals(testVehicleType1.getId(), actualResponse.getVehicleTypeId());
        verify(vehicleTypeRepositoryMock).save(testVehicleType);
    }
}