package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.business.GearboxUseCases.CreateNewGearboxUseCase;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxRequest;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxResponse;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateNewGearboxUseCaseImpl implements CreateNewGearboxUseCase {
    private final GearboxRepository gearboxRepository;

    @Override
    public CreateNewGearboxResponse createNewGearbox(CreateNewGearboxRequest request) {
        GearboxEntity savedGearbox = saveNewGearbox(request);

        return CreateNewGearboxResponse.builder()
                .gearboxId(savedGearbox.getId())
                .gearboxType(savedGearbox.getGearboxType())
                .build();
    }

    private GearboxEntity saveNewGearbox(CreateNewGearboxRequest request) {

        GearboxEntity newGearboxEntity = GearboxEntity.builder()
                .gearboxType(request.getGearboxType())
                .build();
        GearboxEntity savedGearboxEntity = gearboxRepository.save(newGearboxEntity);
        return savedGearboxEntity;
    }
}
