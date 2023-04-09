package com.example.Sem3_CarShop.business.AddUseCases;

import com.example.Sem3_CarShop.domain.AddDomain.GetAllAddsRequest;
import com.example.Sem3_CarShop.domain.AddDomain.GetAllAdvertisementsSpecialResponse;

public interface GetAllAddsUseCase {
    GetAllAdvertisementsSpecialResponse getAllAdds(GetAllAddsRequest request);
}
