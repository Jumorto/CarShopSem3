package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.business.VehicleTypeUseCases.DeleteVehicleTypeUseCase;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteVehicleTypeUseCaseImpl implements DeleteVehicleTypeUseCase {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public void deleteVehicleType(long vehicleTypeId) {
        this.vehicleTypeRepository.deleteById(vehicleTypeId);
    }
}
