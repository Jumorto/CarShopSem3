package com.example.Sem3_CarShop.business.impl.GearboxImpl;

import com.example.Sem3_CarShop.business.GearboxUseCases.DeleteGearboxUseCase;
import com.example.Sem3_CarShop.persistence.GearboxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteGearboxUseCaseImpl implements DeleteGearboxUseCase {
    private final GearboxRepository gearboxRepository;

    @Override
    public void deleteGearbox(long gearboxId) {
        this.gearboxRepository.deleteById(gearboxId);
    }
}
