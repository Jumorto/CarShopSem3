package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.business.EngineTypeUseCases.CreateNewEngineTypeUseCase;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeResponse;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateNewEngineTypeUseCaseImpl implements CreateNewEngineTypeUseCase {
    private final EngineTypeRepository engineTypeRepository;

    @Override
    public CreateNewEngineTypeResponse createNewEngineType(CreateNewEngineTypeRequest request) {
        EngineTypeEntity savedEngineType = saveNewEngineType(request);

        return CreateNewEngineTypeResponse.builder()
                .engineTypeId(savedEngineType.getId())
                .engineType(savedEngineType.getEngineType())
                .build();
    }

    private EngineTypeEntity saveNewEngineType(CreateNewEngineTypeRequest request) {

        EngineTypeEntity newEngineTypeEntity = EngineTypeEntity.builder()
                .engineType(request.getEngineType())
                .build();
        EngineTypeEntity savedEngineTypeEntity = engineTypeRepository.save(newEngineTypeEntity);
        return savedEngineTypeEntity;
    }
}
