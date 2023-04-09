package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.business.VehicleTypeUseCases.CreateNewVehicleTypeUseCase;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeRequest;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.CreateNewVehicleTypeResponse;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateVehicleTypeUseCaseImpl implements CreateNewVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public CreateNewVehicleTypeResponse createNewVehicleType(CreateNewVehicleTypeRequest request) {
        VehicleTypeEntity savedVehicleType = saveNewVehicleType(request);

        return CreateNewVehicleTypeResponse.builder()
                .vehicleTypeId(savedVehicleType.getId())
                .vehicleType(savedVehicleType.getVehicleType())
                .build();
    }

    private VehicleTypeEntity saveNewVehicleType(CreateNewVehicleTypeRequest request) {

        VehicleTypeEntity newVehicleTypeEntity = VehicleTypeEntity.builder()
                .vehicleType(request.getVehicleType())
                .build();
        VehicleTypeEntity savedVehicleType = vehicleTypeRepository.save(newVehicleTypeEntity);
        return savedVehicleType;
    }
}
