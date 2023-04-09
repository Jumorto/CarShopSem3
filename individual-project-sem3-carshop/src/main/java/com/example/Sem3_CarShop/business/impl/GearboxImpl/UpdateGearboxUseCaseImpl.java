package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.business.GearboxUseCases.UpdateGearboxUseCase;
import com.example.Sem3_CarShop.business.exceptions.NotFoundGearboxException;
import com.example.Sem3_CarShop.domain.GearboxDomain.UpdateGearboxRequest;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import com.example.Sem3_CarShop.persistence.entity.GearboxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateGearboxUseCaseImpl implements UpdateGearboxUseCase {
    private final GearboxRepository gearboxRepository;

    @Override
    public void updateGearbox(UpdateGearboxRequest request){
        Optional<GearboxEntity> gearboxOptional = gearboxRepository.findById(request.getId());
        GearboxEntity gearbox;
        if(gearboxOptional.isPresent()){
            gearbox = gearboxOptional.get();
        }else{
            throw new NotFoundGearboxException("GEARBOX_ID_INVALID");
        }
        if(!request.getGearboxType().isEmpty() && request.getGearboxType() != null){
            gearbox.setGearboxType(request.getGearboxType());
        }

        gearboxRepository.save(gearbox);
    }
}
