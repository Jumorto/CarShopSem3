package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.business.VehicleTypeUseCases.UpdateVehicleTypeUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundVehicleTypeException;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.UpdateVehicleTypeRequest;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateVehicleTypeUseCaseImpl implements UpdateVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public void updateVehicleType(UpdateVehicleTypeRequest request){
        Optional<VehicleTypeEntity> vehicleTypeOptional = vehicleTypeRepository.findById(request.getId());
        VehicleTypeEntity vehicleType;
        if(vehicleTypeOptional.isPresent()){
            vehicleType = vehicleTypeOptional.get();
        }else{
            throw new NotFoundVehicleTypeException("VEHICLETYPE_ID_INVALID");
        }
        if(!request.getVehicleType().isEmpty() && request.getVehicleType() != null){
            vehicleType.setVehicleType(request.getVehicleType());
        }

        vehicleTypeRepository.save(vehicleType);
    }
}
