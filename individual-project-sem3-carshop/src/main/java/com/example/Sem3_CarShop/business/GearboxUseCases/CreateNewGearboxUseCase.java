package com.example.Sem3_CarShop.business.GearboxUseCases;

import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxRequest;
import com.example.Sem3_CarShop.domain.GearboxDomain.CreateNewGearboxResponse;

public interface CreateNewGearboxUseCase {
    CreateNewGearboxResponse createNewGearbox(CreateNewGearboxRequest request);
}
