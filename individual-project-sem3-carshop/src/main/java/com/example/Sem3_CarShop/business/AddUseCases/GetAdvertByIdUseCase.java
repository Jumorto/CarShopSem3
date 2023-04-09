package com.example.Sem3_CarShop.business.AddUseCases;

import com.example.Sem3_CarShop.domain.AddDomain.GetAdvertByIdResponse;

public interface GetAdvertByIdUseCase {
    GetAdvertByIdResponse getAdvertById(long id);
}
