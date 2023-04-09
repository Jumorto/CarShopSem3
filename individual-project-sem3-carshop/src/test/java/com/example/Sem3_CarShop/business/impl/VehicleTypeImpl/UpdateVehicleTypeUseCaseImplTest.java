package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.business.exceptions.NotFoundGearboxException;
import com.example.Sem3_CarShop.business.exceptions.NotFoundVehicleTypeException;
import com.example.Sem3_CarShop.domain.GearboxDomain.UpdateGearboxRequest;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.UpdateVehicleTypeRequest;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateVehicleTypeUseCaseImplTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepositoryMock;

    @InjectMocks
    private UpdateVehicleTypeUseCaseImpl updateVehicleTypeUseCase;

    @Test
    void updateVehicleType() {
        VehicleTypeEntity testVehicleType1 = VehicleTypeEntity.builder().id(1L).vehicleType("truck").build();
        when(vehicleTypeRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(testVehicleType1));
        UpdateVehicleTypeRequest request = UpdateVehicleTypeRequest.builder().id(1L).vehicleType("truck").build();
        when(vehicleTypeRepositoryMock.save(testVehicleType1)).thenReturn(testVehicleType1);
        updateVehicleTypeUseCase.updateVehicleType(request);
        verify(vehicleTypeRepositoryMock, times(1)).save(testVehicleType1);
    }

    @Test
    public void testUpdateVehicleType_throwsNotFoundVehicleTypeException_whenVehicleTypeIdIsInvalid() {
        UpdateVehicleTypeRequest request = new UpdateVehicleTypeRequest();
        request.setId(-1l);
        request.setVehicleType("Test Vehcile type");
        when(vehicleTypeRepositoryMock.findById(any())).thenReturn(Optional.empty());
        try{
            updateVehicleTypeUseCase.updateVehicleType(request);
            fail("Expected NotFoundVehicleTypeException to be thrown");
        } catch (NotFoundVehicleTypeException e) {
            //Expected exception, test passes.
        } catch (Exception e) {
            fail("Expected NotFoundVehicleTypeException but got a different exception: " + e.getClass());
        }
    }
}