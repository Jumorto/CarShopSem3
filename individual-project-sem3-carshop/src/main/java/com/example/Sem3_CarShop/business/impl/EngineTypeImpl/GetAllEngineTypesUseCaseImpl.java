package com.example.Sem3_CarShop.business.impl.EngineTypeImpl;

import com.example.Sem3_CarShop.business.EngineTypeUseCases.GetAllEngineTypesUseCase;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.EngineType;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.GetAllEngineTypesResponse;
import com.example.Sem3_CarShop.persistence.EngineTypeRepository;
import com.example.Sem3_CarShop.persistence.entity.EngineTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllEngineTypesUseCaseImpl implements GetAllEngineTypesUseCase {
    private final EngineTypeRepository engineTypeRepository;

    @Override
    public GetAllEngineTypesResponse getAllEngineTypes(){
        List<EngineTypeEntity> results;

        results = engineTypeRepository.findAll();

        final GetAllEngineTypesResponse response = new GetAllEngineTypesResponse();
        List<EngineType> engineTypes = results
                .stream()
                .map(EngineTypeConverter::convert)
                .toList();
        response.setEngineTypes(engineTypes);

        return response;
    }
}
