package com.example.Sem3_CarShop.business.AddUseCases;

import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddRequest;
import com.example.Sem3_CarShop.domain.AddDomain.CreateNewAddResponse;

public interface CreateNewAddUseCase {
    CreateNewAddResponse createNewAdd(CreateNewAddRequest request);
}
