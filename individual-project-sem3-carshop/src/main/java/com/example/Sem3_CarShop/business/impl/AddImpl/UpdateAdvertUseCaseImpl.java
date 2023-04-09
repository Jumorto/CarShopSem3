package com.example.Sem3_CarShop.business.impl.AddImpl;

import com.example.Sem3_CarShop.business.AddUseCases.UpdateAdvertUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundAdvertisementExeption;
import com.example.Sem3_CarShop.domain.AddDomain.UpdateAdvertRequest;
import com.example.Sem3_CarShop.persistence.AddRepository;
import com.example.Sem3_CarShop.persistence.entity.AddEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateAdvertUseCaseImpl implements UpdateAdvertUseCase {

    private final AddRepository addRepository;

    @Override
    public void updateAdvert(UpdateAdvertRequest request){
        Optional<AddEntity> advertOptional = addRepository.findById(request.getId());
        AddEntity advert;
        if(advertOptional.isPresent()){
            advert = advertOptional.get();
        }else{
            throw new NotFoundAdvertisementExeption("ADVERT_ID_INVALID");
        }

        if(request.getName() != null && !request.getName().isEmpty()){
            advert.setName(request.getName());
        }
        if(request.getDescription() != null && !request.getDescription().isEmpty()) {
            advert.setDescription(request.getDescription());
        }
        if(request.getVehicleType() != null) {
            advert.setVehicleType(request.getVehicleType());
        }
        if(request.getBrand() != null) {
            advert.setBrand(request.getBrand());
        }
        if(request.getEngineType() != null) {
            advert.setEngine_type(request.getEngineType());
        }
        if(request.getGearbox() != null) {
            advert.setGearbox(request.getGearbox());
        }
        if(request.getGearbox() != null) {
            advert.setGearbox(request.getGearbox());
        }
        if(request.getNumOfDoors() != null) {
            advert.setNum_doors(request.getNumOfDoors());
        }
        if(request.getDateManufacture() != null){
            advert.setDate_manufacture(request.getDateManufacture());
        }
        if(request.getKilometers() != null) {
            advert.setKilometers(request.getKilometers());
        }
        if(request.getPrice() != null) {
            advert.setPrice(request.getPrice());
        }
        if(request.getDate_publish() != null) {
            advert.setDate_publish(request.getDate_publish());
        }

        addRepository.save(advert);
    }
}
