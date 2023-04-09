package com.example.Sem3_CarShop.business.VehicleTypeUseCases;

import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeRequest;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeResponse;

public interface CreateNewVehicleTypeUseCase {
    CreateNewVehicleTypeResponse createNewVehicleType(CreateNewVehicleTypeRequest request);
}
