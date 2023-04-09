package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.business.EngineTypeUseCases.DeleteEngineTypeUseCase;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEngineTypeUseCaseImpl implements DeleteEngineTypeUseCase {
    private final EngineTypeRepository engineTypeRepository;

    @Override
    public void deleteEngineType(long engineTypeId) {
        this.engineTypeRepository.deleteById(engineTypeId);
    }
}
