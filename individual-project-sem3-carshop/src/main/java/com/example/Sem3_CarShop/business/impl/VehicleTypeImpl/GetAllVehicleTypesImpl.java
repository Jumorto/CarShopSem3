package com.example.Sem3_CarShop.business.impl.VehicleTypeImpl;

import com.example.Sem3_CarShop.business.VehicleTypeUseCases.GetAllVehicleTypesUseCase;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.GetAllVehicleTypesResponse;
import com.example.Sem3_CarShop.domain.VehicleTypeDomain.VehicleType;
import com.example.Sem3_CarShop.persistence.VehicleTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.VehicleTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllVehicleTypesImpl implements GetAllVehicleTypesUseCase {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public GetAllVehicleTypesResponse getAllVehicleTypes(){
        List<VehicleTypeEntity> results;

        results = vehicleTypeRepository.findAll();

        final GetAllVehicleTypesResponse response = new GetAllVehicleTypesResponse();
        List<VehicleType> vehicleTypes = results
                .stream()
                .map(VehicleTypeConverter::convert)
                .toList();
        response.setVehicleTypes(vehicleTypes);

        return response;
    }
}
