package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteVehicleTypeUseCaseImplTest {

    @Mock
    private VehicleTypeRepository vehicleTypeRepositoryMock;
    @InjectMocks
    private DeleteVehicleTypeUseCaseImpl deleteVehicleTypeUseCase;
    @Test
    void deleteVehicleType() {
        long id = 42;
        deleteVehicleTypeUseCase.deleteVehicleType(id);

        verify(vehicleTypeRepositoryMock, times(1)).deleteById(id);
    }
}