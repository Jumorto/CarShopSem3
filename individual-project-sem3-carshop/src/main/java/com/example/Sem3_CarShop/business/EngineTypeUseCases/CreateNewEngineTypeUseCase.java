package com.example.Sem3_CarShop.business.EngineTypeUseCases;

import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeRequest;
import com.example.Sem3_CarShop.domain.EngineTypeDomain.CreateNewEngineTypeResponse;

public interface CreateNewEngineTypeUseCase {
    CreateNewEngineTypeResponse createNewEngineType(CreateNewEngineTypeRequest request);
}
