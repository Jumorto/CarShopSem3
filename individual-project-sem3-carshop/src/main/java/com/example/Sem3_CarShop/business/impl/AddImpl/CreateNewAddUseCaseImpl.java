package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.CreateNewAddUseCase;
import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddRequest;
import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddResponse;
import com.example.Sem3_CarShop.persistence.AddRepository;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateNewAddUseCaseImpl implements CreateNewAddUseCase {
    private final AddRepository addRepository;

    //Creating new advert
    @Override
    public CreateNewAddResponse createNewAdd(CreateNewAddRequest request) {
        AddEntity savedAdd = null;
        savedAdd = saveNewAdd(request);
        return CreateNewAddResponse.builder()
                .addId(savedAdd.getId())
                .build();
    }

    private AddEntity saveNewAdd(CreateNewAddRequest request){

        AddEntity newAddEntity = AddEntity.builder()
                .idUserCreate(request.getIdUserCreate())
                .name(request.getName())
                .description(request.getDescription())
                .vehicleType(request.getVehicleType())
                .brand(request.getBrand())
                .engine_type(request.getEngineType())
                .gearbox(request.getGearbox())
                .num_doors(request.getNumOfDoors())
                .date_manufacture(request.getDateManufacture())
                .kilometers(request.getKilometers())
                .price(request.getPrice())
                .date_publish(Date.from(Instant.now()))
                .build();
        AddEntity savedAddEntity = addRepository.save(newAddEntity);
        return savedAddEntity;
    }
}
